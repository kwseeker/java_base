package top.kwseeker.classLoader;

import java.net.URL;
import java.net.URLClassLoader;

public class HotClassLoader implements IClassLoader {

    @Override
    public ClassLoader createClassLoader(ClassLoader parentClassLoader) {
        URL[] urls = new URL[]{};                               //TODO: 从这里开始创建 类加载器 有问题,参考测试类重新创建类加载器，然后加载改变的类
        return new URLClassLoader(urls, parentClassLoader);
    }

}
