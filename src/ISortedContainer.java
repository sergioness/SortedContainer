import java.util.List;

public interface ISortedContainer<T extends Comparable<? super T>> {

    void add(T element);

    void remove(T element);

    /**
     * Returns first element (element with the smallest value) or null if the
     * collection is empty
     */
    T first();

    /**
     * Returns last element (element with the biggest value) or null if the
     * collection is empty
     */
    T last();

    /**
     * Returns next element (element with a value >= than the argument, but not the
     * same element) or null if there is no matching element
     */
    T next(T element);

    /**
     * Returns previous element (element with a value <= than the argument, but not
     * the same element) or null if there is no matching element
     */
    T prev(T element);

    /**
     * Returns all elements in the sort order
     */
    List<T> asList();
}