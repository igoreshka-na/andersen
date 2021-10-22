import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
    public void testConstructorsAll() {
        MyArrayList<Integer> list1 = new MyArrayList<>(10);
        MyArrayList<Integer> list2 = new MyArrayList<>(0);
        MyArrayList<Integer> list3 = new MyArrayList<>();

        assertEquals(10, list1.size());
        assertEquals(0, list2.size());
        assertEquals(10, list3.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorsThrow() {
        MyArrayList<Integer> list = new MyArrayList<>(-6);
    }

    @Test
    public void testTypeObjectAndSomeMethods() {
        MyArrayList<Integer> list = new MyArrayList<>(10);
        list.add(2);
        assertEquals(Integer.class, list.get(0).getClass());
        assertEquals(0, list.getLastIndex());
    }

    @Test
    public void testPolymorphTypeArray() {
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

    @Test
    public void testTrimToSize() {
        MyArrayList<String> list = new MyArrayList<>(10);
        list.add("1");
        assertEquals(10, list.size());

        list.trimToSize();
        assertEquals(1, list.size());

        list.add("2");
        list.trimToSize();
        assertEquals(2, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.get(1);
    }

    @Test
    public void testCapacityExpansion() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(i);
        }
        assertEquals(16, list.size());
    }

    @Test
    public void testAddElementByIndex1() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.add(5, 10);
        assertEquals(10, list.get(5).intValue());
        assertEquals(4, list.get(4).intValue());
        assertEquals(5, list.get(6).intValue());

        list.add(14, 20);
        assertEquals(20, list.get(11).intValue());
    }

    @Test
    public void testAddElementByIndex2() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(i);
        }
        list.add(5, 10);
        assertEquals(10, list.get(5).intValue());
        assertEquals(4, list.get(4).intValue());
        assertEquals(5, list.get(6).intValue());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionForMethodAddByIndex() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.add(12, 10);
    }

    @Test
    public void testRemoveElement() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.remove(0);
        assertEquals(1, list.get(0).intValue());
        assertEquals(2, list.get(1).intValue());

        list.remove(Integer.valueOf(5));
        assertEquals(4, list.get(3).intValue());
        assertEquals(6, list.get(4).intValue());
    }

    @Test
    public void testSortAndQuickSort() {
        MyArrayList<Integer> list = new MyArrayList<>(10);
        for (int i = 10; i > 0; i--) {
            list.add(i);
        }
        list.sort();
        assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test
    public void testToString() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);

        assertEquals("MyArrayList{elements=[1]}", list.toString());
    }
}
