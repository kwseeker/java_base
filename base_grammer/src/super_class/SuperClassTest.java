package super_class;

import java.util.Date;

/**
 * super.getClass()与getClass()效果是一样的；
 * 都是继承的Object的getClass的本地方法。本地方法的实现是C语言，和类无关。
 * public final native Class<?> getClass();
 */
public class SuperClassTest extends Date {

    public void test() {
        System.out.println(getClass().getName());
        System.out.println(super.getClass().getName());
        System.out.println(super.getClass().getSuperclass().getName());
    }

}
