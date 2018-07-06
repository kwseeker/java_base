package interface_abstractclass;

public class MainTest {
    public static void main(String[] args) {
        A a = new A();
        System.out.println("A继承抽象类的属性值 a=" + a.a);
        a.func();
        A.func2();
        System.out.println("A继承接口的属性值 b=" + a.b);
        a.funcI1();
    }
}
