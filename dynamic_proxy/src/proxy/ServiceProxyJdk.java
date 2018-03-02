package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceProxyJdk implements InvocationHandler {

    // 真实服务对象
    private Object target;

    public Object bind(Object target) {
        this.target = target;

        //获取代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">>>>>>> 这是JDK动态代理 <<<<<<<");

        System.out.println("Before say hello");
        Object result = method.invoke(target, args);
        System.out.println("After say operation");

        return result;
    }
}
