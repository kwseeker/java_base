package use_collection.use_map;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class UseHashTable {

    public static void main(String[] args) {
        Hashtable<String, String> hashtable1 =  new Hashtable<>(4, 0.75f);
        hashtable1.put("A", "Arvin");
        hashtable1.put("B", "Bob");
        hashtable1.put("C", "Cindy");
        hashtable1.put("D", "David");

        Hashtable<String, String> hashtable2 = (Hashtable<String, String>)hashtable1.clone();
        int size = hashtable2.size();

        Enumeration<String> keyEnumerator = hashtable2.keys();
        StringBuilder keys = new StringBuilder();
        while(keyEnumerator.hasMoreElements()) {
            keys.append(keyEnumerator.nextElement());
        }
        Enumeration<String> valueEnumerator = hashtable2.elements();
        StringBuilder values = new StringBuilder();
        while(valueEnumerator.hasMoreElements()) {
            values.append(valueEnumerator.nextElement());
        }

        Set<Map.Entry<String, String>> keyValueSet = hashtable2.entrySet();

        System.out.println(size);
        System.out.println(keys);
        System.out.println(values);
    }
}
