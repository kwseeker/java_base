package top.kwseeker.reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectTest {

    @Test
    public void testReflect() throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = HashMap.class;

        Map<String, Integer> map = (Map<String, Integer>) clazz.newInstance();

        //field
        Field[] fields = clazz.getFields();                     //获取所有public field
        Field[] declaredFields = clazz.getDeclaredFields();     //获取所有field
        Field loadFactorField = clazz.getDeclaredField("loadFactor");
        Assert.assertEquals("loadFactor", loadFactorField.getName());
        Assert.assertEquals(Float.TYPE, loadFactorField.getType());
        Assert.assertEquals(16, loadFactorField.getModifiers());  //各种修饰符值的按位或 PROTECTED | FINAL

        loadFactorField.setAccessible(true);
        loadFactorField.set(map, 0.9f);

        //method
        Method[] methods = clazz.getMethods();
        //注意这里运行时类型擦除，参数类型都是Object.class
        Method putMethod = clazz.getMethod("put", Object.class, Object.class);
        putMethod.invoke(map, "A", 1);
        Assert.assertEquals(1, (int) map.get("A"));

        //constructor
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?> constructor = clazz.getConstructor(Integer.TYPE);
        Map<String, Integer> map1 = (Map<String, Integer>) constructor.newInstance(4);
        putMethod.invoke(map1, "B", 2);
        Assert.assertEquals(2, (int) map1.get("B"));
    }

    //泛型避过类型检查实现给List<Integer>插入字符串数据(这个是使用泛型应该小心避免出现的问题)
    @Test
    public void testEvadeTypeCheck() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Method addMethod = list.getClass().getMethod("add", Object.class);
        Boolean addRet = (Boolean) addMethod.invoke(list, "A");
        Assert.assertTrue(addRet);
    }

    @Test
    public void testGetGenericSuperclass() {
        Type type = HashMap.class.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Type[] types = pt.getActualTypeArguments();
            Assert.assertEquals("K", types[0].getTypeName());
            Assert.assertEquals("V", types[1].getTypeName());
        }

        Type scGenericType = Coffee.class.getGenericSuperclass();
        if (scGenericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) scGenericType;
            Type[] types = pt.getActualTypeArguments();
            Assert.assertEquals("String", types[0].getTypeName());
        }
    }

    @Test
    public void testGenericArray() {
        //Drink<String>[] drinks = new Drink<String>[2];    //错误写法
        Drink<String>[] drinks = (Drink<String>[]) new Drink[2];
    }

    static class Drink<T> {
    }

    static final class Coffee<String> extends Drink<String> {
    }
}
