package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ServiceProxyCglib implements MethodInterceptor {

    private Object target;
    private Enhancer enhancer;

    //创建代理对象
    public Object getInstance(Object target) {
        this.target = target;
        enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(">>>>>>> 这是CGLIB动态代理 <<<<<<<");

        System.out.println("Before say hello");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After say operation");

        return result;
    }

}
