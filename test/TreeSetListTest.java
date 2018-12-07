import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TreeSetListTest {

    private ISortedContainer<StringLen> container;

    private <T extends Comparable<? super T>> void print(ISortedContainer<T> container) {
        StringBuilder temp = new StringBuilder();
        temp.append("TreeSetList{");
        for (T el : container.asList())
            temp.append(el).append(", ");
        temp.append("}");
        temp.replace(temp.length() - 3, temp.length() - 1, "");
        System.out.println(temp);
    }


    @Before
    public void setUp() {

        container = new TreeSetList<>();

        container.add(new StringLen("z1"));
        container.add(new StringLen("z2"));
        container.add(new StringLen("z3"));
        container.add(new StringLen("zzz"));
        container.add(new StringLen("abc"));
        container.add(new StringLen("wtf"));
        container.add(new StringLen("ab"));
        container.add(new StringLen("111"));
        container.add(new StringLen("0"));
        container.add(new StringLen(""));
        container.add(new StringLen("444"));
        container.add(new StringLen("long"));
    }

    @Test
    public void add() {
        TreeSetList<StringLen> treeSetList = (TreeSetList<StringLen>) container;
        Assert.assertEquals("TreeSetList{, 0, z1, z2, z3, ab, zzz, abc, wtf, 111, 444, long}", treeSetList.toString());
    }



    @Test
    public void remove() {
        TreeSetList<StringLen> treeSetList = (TreeSetList<StringLen>) container;
        treeSetList.remove(new StringLen("z1"));
        Assert.assertEquals("TreeSetList{, 0, z2, z3, ab, zzz, abc, wtf, 111, 444, long}", treeSetList.toString());
        treeSetList.remove(new StringLen("z2"));
        Assert.assertEquals("TreeSetList{, 0, z3, ab, zzz, abc, wtf, 111, 444, long}", treeSetList.toString());
        treeSetList.remove(new StringLen("z3"));
        Assert.assertEquals("TreeSetList{, 0, ab, zzz, abc, wtf, 111, 444, long}", treeSetList.toString());
    }

    @Test
    public void first() {
        assertEquals(new StringLen(""), container.first());
    }

    @Test
    public void last() {
        assertEquals(new StringLen("long"), container.last());
    }

    @Test
    public void next() {
        Assert.assertEquals(new StringLen("z3"), container.next(new StringLen("z2")));
    }

    @Test
    public void prev() {
        System.out.println(container);
        Assert.assertNull(container.prev(new StringLen("")));
        Assert.assertEquals("z1", container.prev(new StringLen("z2")).toString());
    }

    @Test
    public void prev2() {
        ISortedContainer<StringLen> container = new TreeSetList<>();

        StringLen z2 = new StringLen("z2");
        StringLen z3 = new StringLen("z3");
        StringLen zzz = new StringLen("zzz");
        container.add(z2);
        container.add(z3);
        container.add(zzz);

        print(container);

        StringLen prev = container.prev(zzz);
        System.out.println(prev);

        Assert.assertEquals("z3", prev.toString());
    }

    @Test
    public void asList() {
        SortedSet<StringLen> control = new TreeSet<>(new Comparator<StringLen>() {
            @Override
            public int compare(StringLen o1, StringLen o2) {
                int cmp = o1.compareTo(o2);
                if (cmp == 0)
                    if (o1.equals(o2))
                        return 0;
                    else
                        return 1;
                else
                    return cmp;
            }
        });
        control.add(new StringLen("z1"));
        control.add(new StringLen("z2"));
        control.add(new StringLen("z3"));
        control.add(new StringLen("zzz"));
        control.add(new StringLen("abc"));
        control.add(new StringLen("wtf"));
        control.add(new StringLen("ab"));
        control.add(new StringLen("111"));
        control.add(new StringLen("0"));
        control.add(new StringLen(""));
        control.add(new StringLen("444"));
        control.add(new StringLen("long"));
        List<StringLen> controlSet = new ArrayList<>(control);
        Assert.assertEquals(controlSet, container.asList());
    }

}