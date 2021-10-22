public interface MyList<T> {
    void sort();
    <T extends Comparable<T>> void quickSort(T[] array, int firstIndex, int lastIndex);
}
