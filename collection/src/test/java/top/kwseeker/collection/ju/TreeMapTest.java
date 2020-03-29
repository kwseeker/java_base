package top.kwseeker.collection.ju;

import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 */
public class TreeMapTest {

    @Test
    public void testTreeMap() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            treeMap.put(String.valueOf(i), i);
        }
        System.out.println("TreeMap cost: " + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            hashMap.put(String.valueOf(i), i);
        }
        System.out.println("HashMap cost: " + (System.currentTimeMillis() - begin));

    }
}
