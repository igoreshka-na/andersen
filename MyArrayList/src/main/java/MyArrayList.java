import java.util.Collection;

public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elements;

    public MyArrayList(int initialSize) {
        if (initialSize < 0) {
            this.size = initialSize;
            this.elements = (T[]) new Object[initialSize];
        } else if (initialSize == 0) {
            this.elements = (T[]) new Object[] {};
        } else throw new IllegalArgumentException("Illegal size: " + initialSize);
    }

    public MyArrayList() {
        this.size = DEFAULT_CAPACITY;
        this.elements = (T[]) new Object[size];
    }

    public MyArrayList(Collection<? extends T> collection) {
        elements = (T[]) collection.toArray();
    }
}
class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>(10);
    }
}
