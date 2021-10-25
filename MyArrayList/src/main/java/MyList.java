import java.util.Comparator;

/**
 * The MyList interface was created to allow implementation of the sort() and quickSort() methods.
 * @author Igor Novikov
 * @param <T> any class inherited from Object.
 */
public interface MyList<T> {
    /**
     * Method of sorting the collection using the Сomparator.
     */
    void sort(Comparator<? super T> comparator);

    /**
     * Method of quick sorting of array by first and last array index.
     * @param array array of elements T.
     * @param firstIndex first index of array.
     * @param lastIndex last index of array.
     * @param <T> any Object class.
     */
    <T> void quickSort(T[] array, int firstIndex, int lastIndex, Comparator<? super T> comparator);
}
