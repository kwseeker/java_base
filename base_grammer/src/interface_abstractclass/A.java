package interface_abstractclass;

public class A extends AbstractClassTest implements InterfaceTest {

    //构造方法：
    A() {}

    //实现AbstractClassTest抽象方法
    @Override
    public void func() {
        System.out.println("执行覆写的抽象类的私有方法");
    }

    @Override
    public void funcI1() {
        System.out.println("执行实现的接口的方法");
    }

    @Override
    public void funcI2() {
        System.out.println("执行实现的抽象类继承接口的方法");
    }
}
