package top.kwseeker.functional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class FunctionInterfaceTest {

    /**
     * 拼接Consumer
     */
    @Test
    public void testConsumerAndThen() {
        Consumer<String> consumer = System.out::println;
        Consumer<String> thenConsumer = System.out::println;
        Consumer<String> composedConsumer = consumer.andThen(thenConsumer); //先执行consumer的accept()再执行thenConsumer的accept()
        composedConsumer.accept("Hello");
    }

    @Test
    public void testBiConsumer() {
        List<Integer> intList = new ArrayList<>();
        BiConsumer<List<Integer>, Integer> biConsumer1 = List::add;
        //BiConsumer<List<Integer>, Integer> biConsumer1 = (list, item) -> list.add(item);
        BiConsumer<List<Integer>, Integer> biConsumer2 = new BiConsumer<List<Integer>, Integer>() {
            @Override
            public void accept(List<Integer> integers, Integer integer) {
                integers.add(integer);
            }
        };
        biConsumer1.accept(intList, 1);
        biConsumer2.accept(intList, 2);
        assertEquals(2, intList.size());
        assertEquals(new Integer(1), intList.get(0));
        assertEquals(new Integer(2), intList.get(1));
    }

    // BinaryOperator 继承 BiFunction, 接收两个同类型的参数，返回一个同类型的参数
    @Test
    public void testBinaryOperator() {
        //List<Integer> list1 = Arrays.asList(1, 2, 3);   //!!! Arrays.asList(1,2,3) 返回的ArrayList不是java.util.ArrayList, 而是Arrays对象内部类
        //List<Integer> list2 = Arrays.asList(4, 5);
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        BinaryOperator<List<Integer>> binaryOperator = (left, right) -> { left.addAll(right); return left; };
        List<Integer> combinedList = binaryOperator.apply(list1, list2);
        assertEquals(2, combinedList.size());
    }
}
