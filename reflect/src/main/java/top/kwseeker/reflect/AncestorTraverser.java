package top.kwseeker.reflect;

/**
 * 对类继承体系(父类，接口)进行遍历
 */
public class AncestorTraverser {

    Class<?> clazz;

    public AncestorTraverser(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void traverseAncestor() {
        traverseAncestor(0, clazz);
    }

    public void traverseAncestor(int depth, Class<?> clazz) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            tabs.append("\t");
        }
        System.out.println(tabs + clazz.getName());
        if (!clazz.isInterface()) {
            Class<?> parentClass = clazz.getSuperclass();
            if (!parentClass.equals(Object.class)) {
                traverseAncestor(depth + 1, parentClass);
            }
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            for (Class<?> anInterface : interfaces) {
                traverseAncestor(depth + 1, anInterface);
            }
        }
    }
}
