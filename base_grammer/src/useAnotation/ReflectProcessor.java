package useAnotation;

import java.lang.reflect.Method;

public class ReflectProcessor {
    public void parseMethod(final Class<?> clazz) throws Exception {
        // new Class[] {}表示创建一个Class数组，并赋初值{}
        // 使用对应的构造器创建新实例，并用new Object[] {}作为初始化参数
        final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            final Reflect my = method.getAnnotation(Reflect.class);
            if (null != my) {
                method.invoke(obj, my.name());
            }
        }
    }
}
