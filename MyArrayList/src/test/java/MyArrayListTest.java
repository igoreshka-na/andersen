import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyArrayListTest {
    private MyArrayList<Object> list = new MyArrayList<>(10);

    @Test
    public void checkSizeList() {
        assertEquals(10, list.size());
    }

    @Test
    public void checkTypeObjects() {
        list.add(new Object());
        assertEquals(Object.class, list.get(0).getClass());
    }
}
