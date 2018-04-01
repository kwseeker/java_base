package access_right;

public class Test {
    public void getStrings() {
        System.out.println("B的静态变量： " + B.str + " " + new B().str);
        System.out.println("B继承平级抽象类D的变量： " + new B().strD);
        System.out.println("C继承平级抽象类D的变量： " + new C().strD);
        System.out.println("C继承平级抽象类D的同名变量： " + new C().str);
        System.out.println("E继承平级抽象类D的str变量： " + new E().str);

        System.out.println("访问同包MultiClass的protect变量： " + new MultiClass().id);
    }
}
