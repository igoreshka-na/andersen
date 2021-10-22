import java.util.Comparator;

/**
 * Интерфейс MyList создан для возможности реализации имплементирующий классов методов sort() и quickSort().
 * @author Igor Novikov
 * @param <T> любой класс наследуемый от Object.
 */
public interface MyList<T> {
    /**
     * Метод сортировки коллекции.
     */
    void sort(Comparator<? super T> comparator);

    /**
     * Метод быстрой сортировки массива по первому и последнему индексу массива.
     * @param array массив элементов T.
     * @param firstIndex первый индекс массива.
     * @param lastIndex последний индекс массива.
     * @param <T> любой класс Object.
     */
    <T> void quickSort(T[] array, int firstIndex, int lastIndex, Comparator<? super T> comparator);
}
