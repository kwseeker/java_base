package top.kwseeker.collection.ju;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayListTest {

    @Test
    public void testGrow() {
        ArrayList<String> list = new ArrayList<>(2);
        list.add("hello");
        list.add("world");
        list.add("lee");    //扩容

        ArrayList<String> list1 = new ArrayList<>();
        list1.add("hello"); //空数组扩容

        Iterator iterator = list.iterator();
    }

    @Test
    public void testSort() {
        Integer[] array = {2, 3, 8, 7, 9, 4, 1};
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if(o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }
}

