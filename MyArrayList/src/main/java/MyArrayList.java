import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс MyArrayList - саморасширяющийся массив с элементами T (generics).
 * Имеет три конструктора: без параметров, со значением размера и с добавлением уже созданной коллекции.
 * Каждый MyArrayList имеет размер свободных ячеек (default 10 ячеек), значение "true/false" первого добавления в список, индекс последнего элемента и массив объектов T.
 * Имплементирует интерфейс MyList с элементами T (generics), и реализует два его метода - sort() и quickSort().
 * Имеет скрытые методы проверки места в массиве - ensureCapacity(), метод саморасширения - capacityExpansion(), метод проверки индекса в диапазоне элементов - rangeCheckElement(int index).
 * Метод size() возвращает размер всего массива, метод getLastIndex() возвращает последний индекс элемента в массиве.
 * Два метода add(): первый - с параметром объекта, который надо добавить (T object); второй - с параметрам указания индекса, в который надо добавить, и параметром самого добавляемого объекта (int index, T object).
 * Метод получения элемента в массиве с указанием индекса - get(int index).
 * Два метода удаления элементов из массива: с указанием конкретного объекта remove(T object), и с указанием индекса remove(int index).
 * Все скрытые методы, а также size(), getLastIndex(), remove(int index) и оба метода add() работают за константное время O(1).
 * Метод get() и remove(T object) работают за время необходимого количества элементов в массиве O(n).
 * Метод sort() и quickSort() работают за логарифмическое время O(log n).
 * @author Igor Novikov
 * @param <T> любой класс Object.
 */
public class MyArrayList<T> implements MyList<T> {
    /**
     * Без указания размера массива, его размер равен 10-ти.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Количество всех ячеек в массиве.
     */
    private int size;
    /**
     * Значение переменной на первое добавление (в начале всегда true).
     */
    private boolean firstAdd = true;
    /**
     * Переменная последнего индекса объекта в массиве (в начале всегда 0).
     */
    private int lastObjectIndex = 0;
    /**
     * Массив эллементов T.
     */
    private T[] elements;

    /**
     * Конструктор класса с установкой изначального размера.
     * Если параметр больше нуля - устанавливает заданный размер.
     * При параметре равном нулю - создется пустой массив (размер равен нулю).
     * При параметре равном меньше нуля выдает исключение IllegalArgumentException.
     * Приводит массив Object к заданному типу T.
     * @param initialSize устнавливаемый размер массива.
     */
    public MyArrayList(int initialSize) {
        if (initialSize > 0) {
            setSize(initialSize);
            elements = (T[]) new Object[size];
        } else if (initialSize == 0) {
            elements = (T[]) new Object[] {};
            setSize(0);
        } else throw new IllegalArgumentException("Illegal size: " + initialSize);
    }

    /**
     * Пустой конструктор со стандартным значением размера.
     */
    public MyArrayList() {
        setSize(DEFAULT_CAPACITY);
        elements = (T[]) new Object[size];
    }

    /**
     * Метод расширения массива.
     * Если массив пустой - то задается значение нового размера 10.
     * Если массив заполнен - рассчитывается новый размер массива и копируется в уже имеющийся, но с новым размером и назначается новый размер по рассчету.
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
     * Проверка массива на необходимость расширения.
     * Если последний индекс массива равен размеру массива, то необходимо расширение.
     * @return значение true при необходимости расширения и false при свободном месте в массиве.
     */
    private boolean ensureCapacity() {
        return lastObjectIndex != size;
    }

    /**
     * Проверка индекса на возможность получения, добавления и удаления элемента по указанному индексу.
     * Если индекс меньше 0 и больше последнего индекса элемента в массиве, то выдает исключение IndexOutOfBoundsException.
     * @param index нужный индекс для метода.
     */
    private void rangeCheckElement(int index) {
        if (index < 0 || lastObjectIndex < index)
            throw new IndexOutOfBoundsException("Index: " + index + ", Elements last index: " + lastObjectIndex);
    }

    /**
     * Закрытый метод установки размера массива.
     * @param newSize новый размер массива.
     */
    private void setSize(int newSize) {
        size = newSize;
    }

    /**
     * Метод получения размера массива.
     * @return значение size.
     */
    public int size() { return size; }

    /**
     * Метод получения последнего индекса с объектом в массиве.
     * @return значение lastObjectIndex.
     */
    public int getLastIndex() { return lastObjectIndex; }

    /**
     * Метод сокращения размера массива до размера количества элементов находящихся в нём.
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
     * Метод добавления элемента в массив.
     * В начале идёт проверка на первое добавление. Если это первое добавление - то сначала расширяется массив, потом добавляется элемент, в конце - изменяется значение firstAdd.
     * Если это не первое добавление, то далее: увеличивается последний индекс, идёт проверка на заполненность массива, и добавляется элемент на последний индекс.
     * @param element указанный элемент на добавление.
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
     * Добавление элемента в массив с указанным индексом, с возможностью добавления элемента в середину массива, а при указании размера массива - возможность добавить элемент в конец массива.
     * Первое - проверяется массив на возможность добавление элемента и, при необходимости, его расширения.
     * Далее идёт проверка на возможность добавление элемента по заданному индексу.
     * Если индекс в диапазоне пустых элементов (больше последнего элемента и меньше размера массива) - то объект добавляется в конец массива.
     * Если индекс больше размера массива - то выдает исключение IndexOutOfBoundsException.
     * Если индекс в диапозоне элементов (от 0 до последнего элемента) - то идёт расширение массива, копирование второй части массива со смещением на один элемент и добавление элемента по указанному индексу.
     * @param index индекс на добавление объекта в указанный индекс массива.
     * @param object объект класса T на добавление.
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
     * Получение элемента массива по указанному индексу.
     * В начале идёт проверка на возможность получения элемента по индексу, и в случае возможности - возвращает объект.
     * @param index необходимый индекс для получения элемента.
     * @return объект по указанному индексу.
     */
    public T get(int index) {
        rangeCheckElement(index);
        return elements[index];
    }

    /**
     * Удаление объекта из массива по указанному индексу.
     * В начале идёт проверка на возможность удаления элемента по индексу.
     * Далее массив копируется с вырезанием нужного элемента в конец массива, где его значению приравнивается null.
     * В конце уменьшается индекс последнего элемента.
     * @param index необходимый индекс на удаление элемента из массива.
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
     * Удаление элемента по указанию самого объекта.
     * Идёт перебор элементов, где находится необходимый. Далее идёт процесс удаления.
     * @param object необходимый объект на удаление из массива.
     */
    public void remove(T object) {
        for (int i = 0; i <= lastObjectIndex; i++) {
            if (object.equals(elements[i])) {
                remove(i);
            }
        }
    }

    /**
     * Метод сортировки массива, где используется метод "быстрой сортировки".
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        quickSort(elements, 0, lastObjectIndex, comparator);
    }

    /**
     * Метод быстрой сортировки массива, имеющего возможность сравнивать элементы.
     * @param array необходимый массив для сортировки.
     * @param firstIndex первый индекс массива.
     * @param lastIndex последний индекс массива.
     * @param <T> тип данных сортировки.
     */
    @Override
    public <T> void quickSort(T[] array, int firstIndex, int lastIndex, Comparator<? super T> comparator) {
        int left = firstIndex;
        int right = lastIndex;
        T pivot = array[(left + right) / 2];

        do {
            while(comparator.compare(array[left], pivot) < 0) left++;
            while(comparator.compare(array[right], pivot) > 0) right--;

            if (left <= right) {
                if(comparator.compare(array[left], array[right]) > 0) {
                    T temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                }
                left++;
                right--;
            }
        } while(left <= right);

        if (left < lastIndex) {
            quickSort(array, left, lastIndex, comparator);
        }
        if (firstIndex < right) {
            quickSort(array, firstIndex, right, comparator);
        }
    }

    /**
     * Метод возвращающий строку со всеми элементами.
     * @return объект класса String со всеми элементами.
     */
    @Override
    public String toString() {
        StringBuilder exit = new StringBuilder();
        exit.append("[");
        for (int i = 0; i <= lastObjectIndex; i++) {
            exit.append(elements[i]);
        }
        exit.append("]");
        return "MyArrayList{" +
                "elements=" +
                exit +
                '}';
    }
}
