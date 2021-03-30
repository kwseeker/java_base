package top.kwseeker.functional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StreamTest {

    @Test
    public void testStreamReduce() {
        // 0 作为累加器的初始值，acc保存当前累加器累加结果，element是当前元素
        int count = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
        int count1 = Stream.of(1, 2, 3).reduce(count, Integer::sum);
        assertEquals(6, count);
        assertEquals(12, count1);
    }

    @Test
    public void testListStream() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        //List<Integer> filteredList =  list.stream().filter(val -> val > 2).collect(Collectors.toList());
        Stream<Integer> stream1 = list.stream();
        Stream<Integer> stream2 = stream1.filter(val -> val > 2);
        List<Integer> filteredList = stream2.collect(Collectors.toList());
    }
}
