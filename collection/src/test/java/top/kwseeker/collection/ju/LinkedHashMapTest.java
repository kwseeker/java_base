package top.kwseeker.collection.ju;

import org.junit.Test;

import java.util.LinkedHashMap;

public class LinkedHashMapTest {

    @Test
    public void testHashMap() {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 200; i++) {
            map.put(i, i);
        }
        System.out.println(map);
    }
}
