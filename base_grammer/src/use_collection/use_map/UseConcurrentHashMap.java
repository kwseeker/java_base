package use_collection.use_map;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class UseConcurrentHashMap {

    private ConcurrentHashMap<String, Integer> concurrentHashMap;

    public void mapInit() {
        concurrentHashMap = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, Integer> getConcurrentHashMap() {
        return this.concurrentHashMap;
    }

    public void checkContent() {
        Enumeration<Integer> enumeration = concurrentHashMap.elements();
        System.out.println("Map contents: ");
        for (; enumeration.hasMoreElements();) {
            System.out.print(" " + enumeration.nextElement());
        }
        System.out.println();
    }

    public void traverseMap() {
        Set<Map.Entry<String, Integer>> set = concurrentHashMap.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
        System.out.println("Map contents: ");
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.print(" " + entry.getKey() + ":" + entry.getValue());
        }
        System.out.println();
    }

    public static void main(String[] args) {

        UseConcurrentHashMap useConcurrentHashMap = new UseConcurrentHashMap();
        useConcurrentHashMap.mapInit();
        ConcurrentHashMap<String, Integer> concurrentHashMap = useConcurrentHashMap.getConcurrentHashMap();

        /**
         * 增
         */
        concurrentHashMap.put("A", 1);
        concurrentHashMap.put("B", 2);
        concurrentHashMap.put("C", 3);

        /**
         * 查
         */
        boolean result;
        result = concurrentHashMap.containsKey("A");
        result = concurrentHashMap.containsKey("F");
        result = concurrentHashMap.contains(2);
        result = concurrentHashMap.containsValue(3);

        useConcurrentHashMap.checkContent();

        System.out.println("Get value by key: " + concurrentHashMap.get("A"));
        //并不会插入
        System.out.println("Get value by Key (or set default value): " + concurrentHashMap.getOrDefault("G" , 0));
        System.out.println("Get hashcode: " + concurrentHashMap.hashCode());
        System.out.println("Get mapping number: " + concurrentHashMap.mappingCount());
        System.out.println("Get value by key: " + concurrentHashMap.get("A"));

        System.out.println("Search value > 5 : " + concurrentHashMap.search(Long.MAX_VALUE,
                (key, value) -> value > 5 ? key : null));

        String result1 = concurrentHashMap.reduce(1,
                (key, value) -> {
                    System.out.println("Transformer: " + Thread.currentThread().getName());
                    return key + "=" + value;
                },
                (s1, s2) -> {
                    System.out.println("Reducer: " + Thread.currentThread().getName());
                    return s1 + "," + s2;
                });
        System.out.println("result: " + result1);

        /**
         * 改
         */
        /*concurrentHashMap.compute("A", new BiFunction<String, Integer, Integer>() {
            @Override
            public Integer apply(String s, Integer integer) {
                return 5;
            }
        });*/
        //首先判断key是否存在存在通过remappingFunction生成新value替换原来的value,否则新建
        concurrentHashMap.compute("F", (key, value) -> 5);
        /*concurrentHashMap.computeIfAbsent("A", new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return null;
            }
        });*/
        //首先判断key是否存在，存在再判断是否已经与值关联，未关联通过remappingFunction生成value,已关联直接退出
        //然后若key不存在，直接创建新的键值对
        concurrentHashMap.computeIfAbsent("C", key -> 2);
        concurrentHashMap.computeIfAbsent("D", key -> 2);
        //首先判断key是否存在存在则通过remappingFunction生成新value替换原来的value
        concurrentHashMap.computeIfPresent("B", (key, value) -> 9);     //改变已存在的键对应的值
        concurrentHashMap.computeIfPresent("E", (key, value) -> 8);     //不会新建键值对
        useConcurrentHashMap.traverseMap();

        System.out.println("Map contents:");
        /*concurrentHashMap.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.print(" " + s + ":" + integer);
            }
        });*/
        concurrentHashMap.forEach((key, value) -> System.out.print(" " + key + ":" + value));
        System.out.println();

        //parallelismThreshold代表顺序处理最大的处理量，当Map键值对数量小于parallelismThreshold则单线程处理
        //当Map键值对不小于阈值则会启动多线程并行处理
        //更详细解释查看javadoc
        concurrentHashMap.forEach(Long.MAX_VALUE, (key, value) -> System.out.print(" " + key + ":" + value));
        System.out.println();
        concurrentHashMap.forEach(1, (key, value) -> System.out.print(" " + key + ":" + value));
        System.out.println();

        concurrentHashMap.forEachEntry(Long.MAX_VALUE, new Consumer<Map.Entry<String, Integer>>() {
            @Override
            public void accept(Map.Entry<String, Integer> stringIntegerEntry) {
                System.out.print(" " + stringIntegerEntry.getKey() + ":" + stringIntegerEntry.getValue());
            }
        });
        System.out.println();
        concurrentHashMap.forEachEntry(Long.MAX_VALUE, new Function<Map.Entry<String,Integer>, Integer>() {
            @Override
            public Integer apply(Map.Entry<String, Integer> entry) {
                return entry.getValue();
            }
        }, new Consumer<Integer>() {
            Integer sumValue = 0;

            @Override
            public void accept(Integer integer) {
                sumValue += integer;
                System.out.print(" " + sumValue);
            }
        });
        System.out.println();

        /*concurrentHashMap.merge("F", 6, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer > integer2 ? integer : integer2;
            }
        });*/
        concurrentHashMap.merge("F", 6 , (oldValue, value) -> (oldValue > value) ? oldValue : value);
        useConcurrentHashMap.traverseMap();

        /**
         * 删
         */
//        concurrentHashMap.clear();

    }

}
