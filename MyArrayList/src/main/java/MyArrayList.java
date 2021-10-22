import java.util.Arrays;
import java.util.Collection;

/**
 * MyArrayList - саморасширяющийся массив с элементами T (generics).
 * Имеет три конструктора: без параметров, со значением размера и с добавлением уже созданной коллекции.
 * Каждый MyArrayList имеет размер свободных ячеек (default 10 ячеек), значение "true/false" первого добавления в список, индекс последнего элемента и массив объектов T.
 * Имплементирует интерфейс MyList с элементами T (generics), и реализует два его метода - sort() и quickSort().
 * Имеет скрытые методы проверки места в массиве - ensureCapacity(), метод саморасширения - capacityExpansion(), метод проверки индекса в диапазоне элементов - rangeCheckElement(int index).
 * Метод size() возвращает размер всего массива, метод getLastIndex() возвращает последний индекс элемента в массиве.
 * Два метода add(): первый - с параметром объекта, который надо добавить (T object); второй - с параметрам указания индекса, в который надо добавить, и параметром самого добавляемого объекта (int index, T object).
 * Метод получения элемента в массиве с указанием индекса - get(int index).
 * Два метода удаления элементов из массива: с указанием конкретного объекта remove(T object), и с указанием индекса remove(int index).
 * Все скрытые методы, а также size(), getLastIndex() работают за константное время O(1).
 * Оба метода add() и метод get() работают за время количества элементов в массиве O(n).
 * Метод sort() и quickSort() работают за логарифмическое время O(log n).
 * @param <T> любой класс наследуемый от Object, где элементы можно сравнить друг с другом (используют Comparable).
 */
public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private boolean firstAdd = true;
    private int lastObjectIndex = 0;
    private T[] elements;

    public MyArrayList(int initialSize) {
        if (initialSize > 0) {
            size = initialSize;
            elements = (T[]) new Comparable[size];
        } else if (initialSize == 0) {
            elements = (T[]) new Comparable[] {};
            size = 0;
        } else throw new IllegalArgumentException("Illegal size: " + initialSize);
    }

    public MyArrayList() {
        size = DEFAULT_CAPACITY;
        elements = (T[]) new Comparable[size];
    }

    public MyArrayList(Collection<?> collection) {
        elements = (T[]) collection.toArray();
        size = elements.length;
        lastObjectIndex = elements.length;
    }

    private void capacityExpansion() {
        int newCapacity;
        if (!firstAdd) {
            newCapacity = (size * 3) / 2 + 1;
        } else {
            newCapacity = 10;
        }
        elements = Arrays.copyOf(elements, newCapacity);
        size = newCapacity;
    }

    private boolean ensureCapacity() {
        return lastObjectIndex != size;
    }

    private void rangeCheckElement(int index) {
        if (0 > index || lastObjectIndex < index)
            throw new IndexOutOfBoundsException("Index: " + index + ", Elements last index: " + lastObjectIndex);
    }

    public int size() { return elements.length; }

    public int getLastIndex() { return lastObjectIndex; }

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

    public void add(int index, T object) {
        if (!ensureCapacity()) {
            capacityExpansion();
            System.arraycopy(elements, index, elements, index + 1, lastObjectIndex - 1);
            elements[index] = object;
            lastObjectIndex++;
        } else {
            if (index > lastObjectIndex && index <= size) {
                lastObjectIndex++;
                elements[lastObjectIndex] = object;
            } else if (index > size) {
                throw new IndexOutOfBoundsException(
                        "Index: " + index + ", Size last index: " + lastObjectIndex);
            } else {
                System.arraycopy(elements, index, elements, index + 1, lastObjectIndex - 2);
                elements[index] = object;
                lastObjectIndex++;
            }
        }
    }

    public T get(int index) {
        rangeCheckElement(index);
        return elements[index];
    }

    public void remove(int index) {
        rangeCheckElement(index);
        int numMoved = lastObjectIndex - index;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[lastObjectIndex] = null;
        lastObjectIndex--;
    }

    public void remove(T object) {
        for (int i = 0; i <= lastObjectIndex; i++) {
            if (object.equals(elements[i])) {
                remove(i);
            }
        }
    }

    @Override
    public void sort() {
        quickSort(elements, 0, lastObjectIndex);
    }

    @Override
    public <T extends Comparable<T>> void quickSort(T[] array, int firstIndex, int lastIndex) {
        int left = firstIndex;
        int right = lastIndex;
        T pivot = array[(left + right) / 2];

        do {
            while(array[left].compareTo(pivot) < 0) left++;
            while(array[right].compareTo(pivot) > 0) right--;

            if (left <= right) {
                if(array[left].compareTo(array[right]) > 0) {
                    T temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                }
                left++;
                right--;
            }
        } while(left <= right);

        if (left < lastIndex) {
            quickSort(array, left, lastIndex);
        }
        if (firstIndex < right) {
            quickSort(array, firstIndex, right);
        }
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
