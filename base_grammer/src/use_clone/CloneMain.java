package use_clone;

import java.io.*;

/**
 * 对象克隆
 *
 * 分类：
 *      浅克隆（克隆的对象成员变量是原对象成员变量的地址[基本类型除外]，修改克隆出来的对象的成员变量会改变原对象的成员变量，因为两者的成员变量使用的是同一个对象引用）
 *      深克隆（克隆的是对象成员变量里面的内容（进入地址更深一层获取里面数据），修改克隆出来的对象成员变量不会对原对象的成员变量造成影响）
 *
 * 意义：
 *      有些情况加可能需要备份类实例的状态；
 *      或者就是想快速构造一个和已有对象相同的副本。
 *
 * 原理：
 *      调用一个对象的clone(), 会自动创建原对象地址空间不同的拷贝，但仅仅只是最外层对象的地址不同；
 *      内部需要控制成员变量的地址不同的话，则要深入内部继续对成员变量的类进行clone()操作。
 *
 * 步骤：
 *      浅拷贝：
 *          只需要外层类实现Cloneable接口，然后覆写clone()，里面调用super.clone()即可。
 *      深拷贝：
 *          外层类实现Cloneable接口，成员变量类也要实现Cloneable接口，成员变量类clone()中调用super.clone();
 *          外层类clone()调用super.clone()和那个成员的clone().
 *          (但这样也仅仅只是两层的深度拷贝，深入成员变量类的成员同样是浅拷贝，除非层层深入，对每一层每一个成员变量都执行clone())。
 */
public class CloneMain {

    public static Object useSerializabledeepClone(Object src){
        Object o = null;
        try{
            if (src != null){
                // 序列化
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(src);
                oos.close();
                // 反序列化
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                o = ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return o;
    }

    public static void main(String[] args) {

        Professor professor = new Professor();
        professor.setName("张三");
        professor.setAge(45);

        InstructorDeepClone instructor = new InstructorDeepClone();
        instructor.setName("李四");
        instructor.setAge(26);

        Student s1 = new Student();
        s1.setName("王五");
        s1.setAge(19);
        s1.setProfessor(professor);
        s1.setInstructor(instructor);

        System.out.println(s1);

        try {
            Student s2 = (Student) s1.clone();
            System.out.println(s2);
            Student s3 = new Student();
            // 克隆对象与被克隆对象内存地址不同，类型相同
            System.out.println("内存地址相等么: " + (s1 == s2) + "\n"
                + "类型相同么：" + (s1.getClass() == s2.getClass()) + "\n"        // 获取Class类对象,相同类型的不同实例，是由相同的Class类实例代理的
                + "同类型实例的Class对象相同么：" + (s1.getClass() == s3.getClass()) + "\n"
//                + "内容相同么：" + (s1.equals(s2)) + "\n"       //对象之间的equals()比较不是比较内容还是比较地址
                + "同类型实例的Class对象的类加载器相同么:" + (s1.getClass().getClassLoader() == s3.getClass().getClassLoader()) + "\n"
                + "基本类型的Class对象的类加载器相同么：" + (int.class.getClassLoader() == char.class.getClassLoader()) + "\n"
                + "int的Class类对象：" + int.class.getName() + "\n"
            );

            // Professor成员只能浅拷贝，InstructorDeepClone可以深拷贝
            // 下面进行测试 s1, s2：
            // 分别改变 s2的 Professor 成员和 InstructorDeepClone 成员，s1会怎样
            Professor p2 = s2.getProfessor();           //返回的Professor对象的引用，s1/s2的内部professor对象是一样的
            InstructorDeepClone i2 = s2.getInstructor();//返回的InstructorDeepClone对象的引用，s1/s2的内部instructor对象是不一样的
            p2.setName("张二");
            p2.setAge(47);
            s2.setProfessor(p2);
            i2.setName("李二");
            i2.setAge(27);
            s2.setInstructor(i2);

            System.out.println("s1和s2的name成员是同一个对象么：" + (s1.getName() == s2.getName()));    //TODO： 为什么String 总是不同的对象？JVM怎么处理的？
            s2.setName("王五五");  //修改s2 String类型成员变量，s1并没有像professor成员变量一样改变
            s2.setAge(20);
            System.out.println("s1和s2的name成员现在还是同一个对象么：" + (s1.getName() == s2.getName())); // String类型，同值则同对象；不同值则不同对象。
            /**
             * http://www.cnblogs.com/xiaoxuetu/archive/2013/06/02/3113427.html
             * String 对象是不可变的，所以可以共享。String对象和基本类型的数据都是存储在常量池的，
             * 它属于方法区的一部分的，作用之一就是存放编译期间生产的各种字面量和符号引用。
             * 方法区的垃圾回收行为是比较少出现的，该区中的对象基本不会被回收，可以理解成是永久存在的。
             * 因此，缓存在字符串缓冲区中的字符串对象基本是不被回收的，而jvm也正是通过复用这些对象从而达到共享作用。
             */

            System.out.println("复制后的：s2 = " + s2);
            System.out.println("复制后的：s1 = " + s1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        /*
        Student [name=王五, age=19, professor=Professor [name=张三, age=45], instructor=Professor [name=李四, age=26]]
        Student [name=王五, age=19, professor=Professor [name=张三, age=45], instructor=Professor [name=李四, age=26]]
        内存地址相等么: false
        类型相同么：true
        同类型实例的Class对象相同么：true
        同类型实例的Class对象的类加载器相同么:true
        基本类型的Class对象的类加载器相同么：true
        int的Class类对象：int

        复制后的：s2 = Student [name=王五, age=19, professor=Professor [name=张二, age=47], instructor=Professor [name=李二, age=27]]
        复制后的：s1 = Student [name=王五, age=19, professor=Professor [name=张二, age=47], instructor=Professor [name=李四, age=26]]
        */

        //使用序列化进行深拷贝：
        Student s4 = (Student) useSerializabledeepClone(s1);
        s4.setName("王六六");
        s4.setAge(18);
        Professor professor1 = s4.getProfessor();
        professor1.setName("张二二");
        professor1 .setAge(48);
        InstructorDeepClone instructor1 = s4.getInstructor();
        instructor1.setName("李四四");
        instructor1.setAge(28);

        System.out.println("复制后的：s4 = " + s4);
        System.out.println("复制后的：s1 = " + s1);
    }
}
