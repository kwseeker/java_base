package use_collection.use_set;

import java.util.HashSet;
import java.util.Set;

public class UseHashSet {

    public static class A {
        public boolean equals(Object obj) {
            return true;
        }
    }
    //类B的hashCode()方法总是返回1,但没有重写其equals()方法
    public static class B {
        public int hashCode() {
            return 1;
        }
    }
    //类C的hashCode()方法总是返回2,但没有重写其equals()方法
    public static class C {
        public int hashCode() {
            return 2;
        }
        public boolean equals(Object obj) {
            return true;
        }
    }

    public static void main(String[] args)
    {
        Set<String> test1 = new HashSet<String>();

        //添加一个字符串对象
//        test1.add(new String("A"));
        test1.add("A");
        test1.add("B");

        //再次添加一个字符串对象，
        //因为两个字符串对象通过equals方法比较相等，所以添加失败，返回false
        boolean result = test1.add("A");
        System.out.println(result);

        //下面输出看到集合只有一个元素
        System.out.println(test1);

        HashSet<Object> test2 = new HashSet<>();
        //分别向books集合中添加2个A对象，2个B对象，2个C对象
        //HashSet以HashCode作为索引，允许HashCode相同的对象加入Set集合
        test2.add(new A());
        test2.add(new A());
        test2.add(new B());
        test2.add(new B());
        test2.add(new C());
        test2.add(new C());
        System.out.println(test2);

        System.out.printf("%x\n", test2.hashCode());  //所有元素hash值的总和

        for (Object obj : test1) {
            System.out.println(obj.toString());
        }
    }

}
