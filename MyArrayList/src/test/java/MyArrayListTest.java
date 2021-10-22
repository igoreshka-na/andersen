import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class MyArrayListTest {

    @Test
    public void checkSizeList() {
        MyArrayList<Integer> list = new MyArrayList<>(10);
        assertEquals(10, list.size());
    }

    @Test
    public void checkTypeObjects() {
        MyArrayList<Integer> list = new MyArrayList<>(10);
        list.add(2);
        assertEquals(Integer.class, list.get(0).getClass());
    }

    @Test
    public void checkPolimorphType() {
        MyArrayList<String> list1 = new MyArrayList<>(1);
        list1.add("1");
        MyArrayList<Integer> list2 = new MyArrayList<>(1);
        list2.add(2);
        MyArrayList<BigInteger> list3 = new MyArrayList<>(1);
        list3.add(BigInteger.valueOf(125253));

        assertEquals(String.class, list1.get(0).getClass());
        assertEquals(Integer.class, list2.get(0).getClass());
        assertEquals(BigInteger.class, list3.get(0).getClass());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkException() {

    }

    @Test
    public void checkQuickSort() {
        MyArrayList<Integer> list = new MyArrayList<>(10);
        for (int i = 10; i > 0; i--) {
            list.add(i);
        }
        list.sort();
        System.out.println();
        assertEquals(Integer.valueOf(1), list.get(0));
    }
}
