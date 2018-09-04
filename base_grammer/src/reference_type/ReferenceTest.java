package reference_type;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 软引用会尽可能长时间的驻留在JVM中，只在GC多次之后实在内存不足才会被回收，常用作缓存
 * 弱引用对象在下一次GC时会被回收，也多用于缓存，经典的使用是WeakHashMap
 * 虚引用对象也是在下一次GC时被回收，主要用于跟踪垃圾回收过程
 *
 * 当一个对象有多个引用时，生存时间按最强的引用算。
 */
public class ReferenceTest {

    private class MyObject {
        private String objectName = "MyObject";
    }

    public static void main(String[] args) {

        //强引用
        MyObject object1 = new ReferenceTest().new MyObject();  //object1 作为 GC Roots
        System.out.println(object1);
        // object1 = null;      //这里不置空，或程序不执行结束，或程序不被杀死，则object1指向的对象将一直存在
        System.gc();
        System.out.println(object1);

        //软引用
        MyObject object2 = new ReferenceTest().new MyObject();
        SoftReference<MyObject> srReference = new SoftReference<>(object2);   //创建软引用
        object2 = null;
        System.out.println(srReference.get());
        System.gc();
        System.out.println(srReference.get());  //一般情况还是会打印出原来的对象，可以修改VM Options, 强制内存不足，则会打印null

        //弱引用
        MyObject object3 = new ReferenceTest().new MyObject();
        WeakReference<MyObject> wrReference = new WeakReference<>(object3);   //创建弱引用
        object3 = null;
        System.out.println(wrReference.get());
        System.gc();
        System.out.println(wrReference.get());  //这里将打印null
    }
}
