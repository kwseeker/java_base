package top.kwseeker.collection.util;

import java.util.Random;

public class RandomUtil {

    public static int get(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }
}
