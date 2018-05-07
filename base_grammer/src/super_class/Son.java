package super_class;

public class Son extends Father {
    public Son() {      // 没有显式写super()时默认调用父类无参构造函数
        // super();     // 这里注释与否效果都一样
        System.out.println("Son class is initial");
    }

    public Son(int a) { // 只有调带参数的父类构造函数时，写super(xxx); 才有意义
        super(a);
        System.out.println("Son class is initial");
    }
}
