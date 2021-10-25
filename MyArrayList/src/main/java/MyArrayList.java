import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * The MyArrayList class is a self-expanding array with T elements (generics).
 *
 * All hidden methods as well as size(), getLastIndex(), remove(int index) and both add() methods work for a constant O(1) time.
 * The get() and remove(T object) methods work for the required number of elements in the array O(n) time.
 * The sort() and quickSort() methods work for logarithmic time O(n log n).
 *
 * @author Igor Novikov
 * @param <T> any Object class.
 */
public class MyArrayList<T> implements MyList<T> {
    /**
     * Without specifying an array size, its size is 10.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * The number of all cells in the array.
     */
    private int size;
    /**
     * The value of the variable for the first addition (always true at the beginning).
     */
    private boolean firstAdd = true;
    /**
     * Variable of the last object index in the array (always 0 at the beginning).
     */
    private int lastObjectIndex = 0;
    /**
     * An array of elements T.
     */
    private T[] elements;

    /**
     * Class constructor with initial size setting.
     *
     * @param initialSize the size of the array to be set.
     */
    public MyArrayList(int initialSize) {
        if (initialSize > 0) {
            setSize(initialSize);
            elements = (T[]) new Object[size];
        } else if (initialSize == 0) {
            elements = (T[]) new Object[]{};
            setSize(0);
        } else throw new IllegalArgumentException("Illegal size: " + initialSize);
    }

    /**
     * Empty constructor with a standard size value.
     */
    public MyArrayList() {
        setSize(DEFAULT_CAPACITY);
        elements = (T[]) new Object[size];
    }

    /**
     * Constructor with bringing another collection to class T.
     *
     * @param collection other collection.
     */
    public MyArrayList(Collection<? extends T> collection) {
        Object[] a = collection.toArray();
        size = collection.size();
        lastObjectIndex = size - 1;
        elements = (T[]) Arrays.copyOf(a, size, Object[].class);
    }

    /**
     * Array expansion method.
     */
    private void capacityExpansion() {
        int newCapacity;
        if (!firstAdd) {
            newCapacity = (size * 3) / 2 + 1;
        } else {
            newCapacity = 10;
        }
        elements = Arrays.copyOf(elements, newCapacity);
        setSize(newCapacity);
    }

    /**
     * Check the array to see if it needs to be expanded.
     *
     * @return value is true if the array needs to be expanded, and false if there is free space in the array.
     */
    private boolean ensureCapacity() {
        return lastObjectIndex != size;
    }

    /**
     * Check the index for the possibility to get, add and delete an element by the specified index.
     *
     * @param index required index for the method.
     */
    private void rangeCheckElement(int index) {
        if (index < 0 || lastObjectIndex < index)
            throw new IndexOutOfBoundsException("Index: " + index + ", Elements last index: " + lastObjectIndex);
    }

    /**
     * A closed method of setting the size of the array.
     *
     * @param newSize new size of the array.
     */
    private void setSize(int newSize) {
        size = newSize;
    }

    /**
     * The method of obtaining the size of the array.
     *
     * @return size value.
     */
    public int size() {
        return size;
    }

    /**
     * A method to get the last index with an object in the array.
     *
     * @return value lastObjectIndex.
     */
    public int getLastIndex() {
        return lastObjectIndex;
    }

    /**
     * A method of reducing the size of an array to the number of elements it contains.
     */
    public void trimToSize() {
        System.arraycopy(elements, 0, elements, 0, lastObjectIndex);
        if (lastObjectIndex == 0) {
            setSize(1);
        } else {
            setSize(lastObjectIndex + 1);
        }
    }

    /**
     * The method of adding an element to an array.
     *
     * @param element specified element to add.
     */
    public void add(T element) {
        if (firstAdd) {
            capacityExpansion();
            elements[lastObjectIndex] = element;
            firstAdd = false;
        } else {
            lastObjectIndex++;
            if (ensureCapacity()) {
                elements[lastObjectIndex] = element;
            } else {
                capacityExpansion();
                elements[lastObjectIndex] = element;
            }
        }
    }

    /**
     * Adding an element to the array with the specified index, with the possibility to add an element to the middle of the array, and if you specify an array size - the possibility to add an element to the end of the array.
     *
     * @param object object of class T to add.
     */
    public void add(int index, T object) {
        if (index > lastObjectIndex && index <= size) {
            add(object);
        } else if (index > size || 0 > index) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size last index: " + lastObjectIndex);
        } else {
            capacityExpansion();
            System.arraycopy(elements, index - 1, elements, index, lastObjectIndex - 1);
            elements[index] = object;
            lastObjectIndex++;
        }
    }

    /**
     * Getting an element of an array by the specified index.
     *
     * @param index necessary index for getting an element.
     * @return object by the specified index.
     */
    public T get(int index) {
        rangeCheckElement(index);
        return elements[index];
    }

    /**
     * Deleting an object from an array by the specified index.
     *
     * @param index required index to remove an element from the array.
     */
    public void remove(int index) {
        rangeCheckElement(index);
        int numMoved = lastObjectIndex - index;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[lastObjectIndex] = null;
        lastObjectIndex--;
    }

    /**
     * Deleting an element by specifying the object itself.
     *
     * @param object necessary object to remove from the array.
     */
    public void remove(T object) {
        for (int i = 0; i <= lastObjectIndex; i++) {
            if (object.equals(elements[i])) {
                remove(i);
            }
        }
    }

    /**
     * Array sorting method, where the "quick sort" method is used.
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        quickSort(elements, 0, lastObjectIndex, comparator);
    }

    /**
     * A quick sort method for an array that has the ability to compare elements.
     *
     * @param array required array for sorting.
     * @param firstIndex first index of the array.
     * @param lastIndex last index of array.
     * @param <T> sort data type.
     */
    @Override
    public <T> void quickSort(T[] array, int firstIndex, int lastIndex, Comparator<? super T> comparator) {
        int left = firstIndex;
        int right = lastIndex;
        T pivot = array[(left + right) / 2];

        do {
            while (comparator.compare(array[left], pivot) < 0) left++;
            while (comparator.compare(array[right], pivot) > 0) right--;

            if (left <= right) {
                if (comparator.compare(array[left], array[right]) > 0) {
                    T temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                }
                left++;
                right--;
            }
        } while (left <= right);

        if (left < lastIndex) {
            quickSort(array, left, lastIndex, comparator);
        }
        if (firstIndex < right) {
            quickSort(array, firstIndex, right, comparator);
        }
    }

    /**
     * A method that returns a string with all elements.
     *
     * @return object of class String with all elements.
     */
    @Override
    public String toString() {
        return "MyArrayList{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
