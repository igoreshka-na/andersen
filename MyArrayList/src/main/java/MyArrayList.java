import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int lastObject = 0;
    private T[] elements;

    public MyArrayList(int initialSize) {
        if (initialSize > 0) {
            size = initialSize;
            elements = (T[]) new Object[size];
        } else if (initialSize == 0) {
            elements = (T[]) new Object[] {};
            size = 0;
        } else throw new IllegalArgumentException("Illegal size: " + initialSize);
    }

    public MyArrayList() {
        size = DEFAULT_CAPACITY;
        elements = (T[]) new Object[size];
    }

    public MyArrayList(Collection<?> collection) {
        elements = (T[]) collection.toArray();
        size = elements.length;
        lastObject = elements.length;
    }

    private void capacityExpansion() {
        int newCapacity = (size * 3) / 2 + 1;
        elements = Arrays.copyOf(elements, newCapacity);
        size = newCapacity;
    }

    private boolean ensureCapacity() {
        return lastObject != size;
    }

    private void rangeCheckElement(int index) {
        if (0 > index || lastObject < index)
            throw new IndexOutOfBoundsException("Index: " + index + ", Elements last index: " + lastObject);
    }

    public int size() { return size; }

    public void add(T element) {
        if (ensureCapacity()) {
            elements[lastObject] = element;
            lastObject++;
        } else {
            capacityExpansion();
            elements[lastObject] = element;
            lastObject++;
        }
    }

    public void add(int index, T object) {
        if (!ensureCapacity()) {
            capacityExpansion();
            System.arraycopy(elements, index, elements, index + 1, size - 1);
            elements[index] = object;
            lastObject++;
        } else {
            System.arraycopy(elements, index, elements, index + 1, size - 1);
            elements[index] = object;
            lastObject++;
        }
    }

    public T get(int index) {
        rangeCheckElement(index);
        return elements[index];
    }
}
class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>(10);
        for (int i = 0; i < 14; i++) {
            list.add(String.valueOf(i));
            System.out.println(i + " - " + list.size());
        }
        list.add(0, "full");
        System.out.println(list.get(0));
        System.out.println();
        list.get(1);
    }
}
