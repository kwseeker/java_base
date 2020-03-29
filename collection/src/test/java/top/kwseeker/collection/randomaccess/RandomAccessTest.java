package top.kwseeker.collection.randomaccess;

import org.junit.Test;
import top.kwseeker.collection.util.RandomUtil;

import java.util.*;

/**
 * RandomAccess
 * RandomAccess对数据的访问都基于数组，然后可以通过索引、位移对数据快速地检索
 */
public class RandomAccessTest {

    @Test
    public void testRandomAccess() {
        ArrayList<Integer> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(RandomUtil.get(100));
        }
        list.add(82);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        for (int item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
        int targetIndex = Collections.binarySearch(list, 82);
        System.out.println(targetIndex);
    }
}
