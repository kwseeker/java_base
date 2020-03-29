package top.kwseeker.collection.algorithm;

import org.junit.Test;
import top.kwseeker.collection.algorithm.MyTimeSort;

/**
 * 实现类似与ForkJoin的分而治之
 * 借助递归实现，假设从小到大排序
 */
public class MyTimSortTest {

    @Test
    public void testSort() {
        int[] test = {3, 2, 9, 7, 6, 1, 4, 5, 8};
        int[] ret = MyTimeSort.sort(test);
        for (int val : ret) {
            System.out.println(val);
        }
    }
}
