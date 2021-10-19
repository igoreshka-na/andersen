public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private int size;
    private Object[] elements;

    public MyArrayList(int initialSize) {
        if (initialSize < 0) {
            this.size = initialSize;
            this.elements = new Object[initialSize];
        } else if (initialSize == 0) {
            this.elements = new Object[] {};
        } else throw new IllegalArgumentException("Illegal size: " + initialSize);
    }

    public MyArrayList() {
        this.size = DEFAULT_CAPACITY;
        this.elements = new Object[DEFAULT_CAPACITY];
    }
}
