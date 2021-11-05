import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CompanyControllerTest {
    private static CRUDcompany crud;
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static RequestDispatcher dispatcher;
    private static CompanyController controller;

    // Collection to store HttpServletRequest keys/values attributes
    private static final Map<String, Object> attributes = new HashMap<>();


    @BeforeAll
    public static void setup() throws SQLException {
        //setup DaoPlayers
        crud = mock(CRUDcompany.class);
        when(crud.findAll()).thenReturn(CRUDcompanyTestData.companies);
        when(crud.find(CRUDcompanyTestData.COMPANY_1_ID)).thenReturn(CRUDcompanyTestData.company1);
        when(crud.delete(anyInt())).thenReturn(true);
        when(crud.find(4)).thenReturn(CRUDcompanyTestData.getNew());
        //setup servlets objects
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        //setup controller
        controller = new CompanyController();

        //https://stackoverflow.com/a/30726246/16047333
        // Mock setAttribute
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArgument(0);
                Object value = invocation.getArgument(1);
                attributes.put(key, value);
                return null;
            }
        }).when(request).setAttribute(Mockito.anyString(), Mockito.any());

        // Mock getAttribute
        Mockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArgument(0);
                return attributes.get(key);
            }
        }).when(request).getAttribute(Mockito.anyString());
    }

    @AfterEach
    void resetAttributeStorage() {
        attributes.clear();
    }

    @Test
    void doGet_ActionDefault_ListOfPlayers() throws Exception {
        //reset only the number of invocations
        clearInvocations(dispatcher);
        //define mocks behavior for our case
        when(request.getServletPath()).thenReturn("");
        when(request.getRequestDispatcher("WEB-INF/jsp/company-list.jsp")).thenReturn(dispatcher);
        controller.doGet(request, response);

        //check request attributes
        Assertions.assertEquals(CRUDcompanyTestData.companies, request.getAttribute("listCompany"));
        // verify called methods
        verify(dispatcher, times(1)).forward(request, response);
    }


    @Test
    void doGet_Action_Delete() throws Exception {
        clearInvocations(response);

        when(request.getServletPath()).thenReturn("/delete");
        when(request.getParameter("id")).thenReturn(String.valueOf(CRUDcompanyTestData.COMPANY_1_ID));
        when(request.getRequestDispatcher("WEB-INF/jsp/company-list.jsp")).thenReturn(dispatcher);

        controller.doGet(request, response);

        verify(response, times(1)).sendRedirect("list");
    }

    @Test
    void doGet_Action_NewForm() throws Exception {
        clearInvocations(dispatcher);

        when(request.getServletPath()).thenReturn("/new");
        when(request.getRequestDispatcher("WEB-INF/jsp/company-form.jsp")).thenReturn(dispatcher);

        controller.doGet(request, response);

        verify(dispatcher, times(1)).forward(request, response);
    }
}