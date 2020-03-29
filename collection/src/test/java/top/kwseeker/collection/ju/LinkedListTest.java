package top.kwseeker.collection.ju;

import org.junit.Test;
import top.kwseeker.collection.util.RandomUtil;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

    @Test
    public void testLinkedList() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            int randNum = RandomUtil.get(100);
            list.add(randNum);
            System.out.print(randNum + " ");
        }
        System.out.println();
        //先`LinkedList$toArray()`将链表转换为数组，然后执行Arrays.sort()排序，即还是归并排序;
        //然后将数组的数据一一回写到LinkedList中。
        list.sort(Integer::compareTo);

        for (Integer item : list) {
            System.out.print(item + " ");
        }
    }
}
