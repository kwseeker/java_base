package useClass;

public class MainTest {
    public static void main(String args[]) {
        try {
            // 获取类ClassTest的Class对象的三种方法
            // 方法1
            Class c1 = Class.forName("useClass.ClassTest"); //forName()方法自带显示类加载功能
            // 方法2
            ClassTest classTest0 = new ClassTest();
            Class c2 = classTest0.getClass();
            // 方法3
            Class c3 = ClassTest.class;

            //上面三种方法获得的是同一个Class对象实例
            System.out.println("三种方法获取的实例是否为同一个：" + ((c1==c2)&&(c2==c3)));

            System.out.println("ClassTest的基本信息：");
            System.out.print(c1.toString() + "\n"
                + c1.toGenericString() + "\n"
                + c1.getName() + "\n"
                + c1.getClassLoader().toString() + "\n"     // 在Java WEB项目编程时，往往通过classLoader获取Jar包的物理路径
            );

            //通过ClassTest的Class类实例化新的实例
            ClassTest classTest = (ClassTest) c1.newInstance(); //newInstance()无参，只能调用类的无参构造函数
            classTest.setUsername("Lee");
            classTest.setId("0315");
            System.out.println(classTest.toString() + "\n"
                + "isInstance: " + (c1.isInstance(classTest)?"true":"false") + "\n"
                + "isInterface: " + (c1.isInterface()?"ture":"false") + "\n"
                + "isPrimitive: " + (c1.isPrimitive()?"ture":"false") + "\n"
            );

            //获取类加载器
            ClassLoader classLoader = ClassTest.class.getClassLoader();
            System.out.println("ClassTest的类加载器： " + classLoader + "ClassTest的Class类名：" + ClassTest.class.getName());
            //尝试重新加载
            Class c4 = Class.forName("useClass.ClassTest", true, classLoader);
            System.out.println("新创建的Class类实例与前面的是否为同一个：" + (c1==c4));  //实际上还是获取的已创建的那个Class实例
            //使用ClassTest的类加载器加载ClassBTest,并创建实例
//            ClassBTest classBTest = (ClassBTest)classLoader.getParent().loadClass("useClass.ClassBTest").newInstance();   //父加载器找不到这个类
            ClassBTest classBTest = (ClassBTest)classLoader.loadClass("useClass.ClassBTest").newInstance();
            classBTest.func();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
