import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeSetListTest {

    private ISortedContainer<StringLen> container;

    private void print(){
        StringBuilder temp = new StringBuilder();
        temp.append("TreeSetList{");
        for (StringLen el : container.asList())
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
    public void next() {
        container.next(new StringLen("z2"));
        print();
    }

    @Test
    public void add() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void first() {
    }

    @Test
    public void last() {
    }


    @Test
    public void prev() {
    }

    @Test
    public void asList() {
    }
}