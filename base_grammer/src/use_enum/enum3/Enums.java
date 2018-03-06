package use_enum.enum3;

import java.util.Random;

public class Enums {

    private static Random rand = new Random(47);      //内部类不能使用静态方法

//    public static <T extends Enum<T>> T random(Class<T> ec) {
//        return random(ec.getEnumConstants());
//    }

    public static  <T> T random(T[] values) {
        return values[rand.nextInt(values.length)]; //生成介于[0,values.length)的整数
    }

}
