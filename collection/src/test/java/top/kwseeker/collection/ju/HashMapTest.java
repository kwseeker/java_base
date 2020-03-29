package top.kwseeker.collection.ju;

import org.junit.Test;

import java.util.HashMap;

/**
 *
 */
public class HashMapTest {

    @Test
    public void testHashMap() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 200; i++) {
            map.put(i, i);
        }
        System.out.println(map);
    }

    @Test
    public void testHashMapCollision() {
        HashMap<MyObject, Integer> map = new HashMap<>();
        System.out.println(new MyObject().equals(new MyObject()));
        for (int i = 0; i < 200; i++) {
            map.put(new MyObject(), i);
        }
    }
    public static class MyObject {
        @Override
        public int hashCode() {
            return 1;
        }
    }
}
