package access_right;

/**
 * 一个.java文件可以有多个同级类，其中只能有一个public且名字与文件名相同的类，平级类默认访问权限（包可访问）
 * 内部平级类只能用 public／abstract／final／无修饰符 修饰；
 */
public class MultiClass {

    protected int id = 2;

    //获取内部平级类
    public void getStrings() {
        System.out.println("平级类B的静态变量： " + B.str + " " + new B().str);
        System.out.println("平级类B继承平级抽象类D的变量： " + new B().strD);
        System.out.println("平级类C继承平级抽象类D的变量： " + new C().strD);
        System.out.println("平级类C继承平级抽象类D的同名变量： " + new C().str);
        System.out.println("平级类E继承平级抽象类D的str变量： " + new E().str);
    }
}

class B extends D {     // str strD
    static final String str = "b";  //覆盖继承来的str
}

final class C extends D {   // str strD
    String str = "c";       //相当于被覆盖了
}

class E extends D {
}

abstract class D {
    String strD = "d";
    String str = "e";
}
