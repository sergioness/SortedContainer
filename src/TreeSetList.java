import java.util.*;

public class TreeSetList<T extends Comparable<? super T>> implements ISortedContainer<T> {

    private final Comparator<T> comparator;

    private TreeMap<T, LinkedList<T>> tree;

    public TreeSetList() {
        this.comparator = null;
    }

    public TreeSetList(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(T element) {
        if (tree == null)
            tree = new TreeMap<>(comparator);
        Map.Entry<T, LinkedList<T>> floor = tree.floorEntry(element);
        if (floor != null) {
            int cmp;
            if (comparator == null)
                cmp = element.compareTo(floor.getKey());
            else
                cmp = comparator.compare(floor.getKey(), element);
            if (cmp == 0) {
                if (!floor.getKey().equals(element)) {
                    LinkedList<T> list = floor.getValue();
                    if (list != null)
                        list.addLast(element);
                    else {
                        list = new LinkedList<>();
                        list.addLast(element);
                        floor.setValue(list);
                    }
                }
                return;
            }
        }
        tree.put(element, new LinkedList<>());
    }

    @Override
    public void remove(T element) {
        Map.Entry<T, LinkedList<T>> entry = tree.floorEntry(element);
        if (entry == null)
            return;
        T entryKey = entry.getKey();
        LinkedList<T> entryValue;
        if (entryKey.equals(element)) {
            entryValue = tree.remove(element);
            T head = entryValue.poll();
            if (head != null)
                tree.put(head, entryValue);
        } else {
            entryValue = entry.getValue();
            if (entryValue != null && !entryValue.isEmpty())
                for (T el : entryValue)
                    if (el.equals(element)) {
                        entryValue.remove(element);
                        return;
                    }
        }
    }

    @Override
    public T first() {
        return tree.firstKey();
    }

    @Override
    public T last() {
        return tree.lastKey();
    }

    @Override
    public T next(T element) {
        Map.Entry<T, LinkedList<T>> ceiling = tree.ceilingEntry(element);
        if (ceiling == null)
            return null;
        int cmp;
        if (comparator == null)
            cmp = element.compareTo(ceiling.getKey());
        else
            cmp = comparator.compare(ceiling.getKey(), element);
        if (cmp == 0) {
            LinkedList<T> value = ceiling.getValue();
            if (value != null && !value.isEmpty()) {
                if (element.equals(ceiling.getKey()))
                    return value.peek();
                else {
                    // traverse list to find appropriate value that greater than the given
                    T that;
                    Iterator<T> it = value.listIterator();
                    while (it.hasNext())
                        if (it.next().equals(element))
                            break;
                    that = it.hasNext() ? it.next() : null;
                    if (that != null)
                        return that;
                }
            }
            return tree.higherKey(element);
        }
        return ceiling.getKey();
    }

    @Override
    public T prev(T element) {
        Map.Entry<T, LinkedList<T>> floor = tree.floorEntry(element);
        if (floor == null)
            return null;
        int cmp;
        if (comparator == null)
            cmp = element.compareTo(floor.getKey());
        else
            cmp = comparator.compare(floor.getKey(), element);
        if (cmp == 0) {
            LinkedList<T> value = floor.getValue();
            if (value != null && !value.isEmpty()) {
                if (element.equals(floor.getKey()))
                    return tree.lowerKey(element);
                else {
                    // traverse list to find appropriate value that smaller than the given
                    T that;
                    ListIterator<T> it = value.listIterator();
                    while (it.hasNext())
                        if (it.next().equals(element))
                            break;
                    it.previous();
                    that = it.hasPrevious() ? it.previous() : floor.getKey();
                    return that;
                }
            }
            return tree.lowerKey(element);
        }
        return floor.getKey();
    }

    @Override
    public List<T> asList() {
        LinkedList<T> list = new LinkedList<>();
        for (Map.Entry<T, LinkedList<T>> entry : tree.entrySet()) {
            list.addLast(entry.getKey());
            for (T el : entry.getValue())
                list.addLast(el);
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append("TreeSetList{");
        for (T el : asList())
            temp.append(el).append(", ");
        temp.append("}");
        temp.replace(temp.length() - 3, temp.length() - 1, "");
        return temp.toString();
    }

}
