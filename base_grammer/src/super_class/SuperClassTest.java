package super_class;

import java.util.Date;

/**
 * super.getClass()与getClass()是一样的；
 * 都是继承的Object的getClass方法。
 */
public class SuperClassTest extends Date {

    public void test() {
        System.out.println(getClass().getName());
        System.out.println(super.getClass().getName());
        System.out.println(super.getClass().getSuperclass().getName());
    }

}
