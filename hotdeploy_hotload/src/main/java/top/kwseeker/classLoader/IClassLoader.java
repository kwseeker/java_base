package top.kwseeker.classLoader;

public interface IClassLoader {

    ClassLoader createClassLoader(ClassLoader parentClassLoader);
}
