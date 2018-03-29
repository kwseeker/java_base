package use_inner_class;

/**
 * 静态内部类，狭义上已经不是内部类了，与普通类在运行时的行为和功能上没有什么区别；
 * 可以定义为public/protected/默认/private
 * 注意引用方法： 外部类名.内部类名
 */
public class StaticOuter {

    private int a = 100;
    private static int b = 150;
    public static void test(){
        System.out.println("Outer static test ...");
    }
    public  void test2(){
        System.out.println("Outer instabce test ...");
    }

    static class StaticInner {
        public  int a = 200;
        static int b =300;
        public static void test(){
            System.out.println("Inner static test ...");
        }
        public  void test2(){
            System.out.println("Inner instance test ...");
            StaticOuter.test();
            new StaticOuter().test2();
            System.out.println("StaticOuter.b  = "+StaticOuter.b);
        }
    }

}
