package use_inner_class;

/**
 * 局部内部类
 */
public class LocalOuter {
    private int a = 1;
    private static int b = 2;

    public void test() {
        final int c = 3;
        class LocalInner {
            public void add1() {
                System.out.println("a= " + a);
                System.out.println("b= " + b);
                System.out.println("c= " + c);
            }
        }
        new LocalInner().add1();
    }

    static public void test2() {
        final int d = 5;
        class LocalInner2 {
            public void add1() {
                // System.out.println("a= " + a);
                System.out.println("b= " + b);
                System.out.println("c= " + d);
            }
        }
        new LocalInner2().add1();
    }
}
