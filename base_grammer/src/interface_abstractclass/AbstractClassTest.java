package interface_abstractclass;

public abstract class AbstractClassTest {

    //属性
    int a = 10;

    //构造方法：不能被继承，不能被abstract修饰
    AbstractClassTest() {
        System.out.println("AbstractClassTest类构造器被执行");
    }

    //抽象方法
    abstract void func();

    //私有方法：不可被继承即也不能被abstract修饰
    private void func1() {
        System.out.println("执行抽象类的私有方法");
    }

    //静态方法
    static void func2() {
        System.out.println("执行抽象类的静态方法");
    }
}
