package top.kwseeker.functional;

import java.util.Objects;
import java.util.function.Supplier;

public class SupplierDemo<T> {

    private T value;

    public T get() {
        if(value != null) {
            return value;
        }
        return initialValue();
    }

    protected T initialValue() {
        return null;
    }

    /**
     * 创建带有默认值的实例
     * 这个S是static泛型方法的写法
     */
    public static <S> SupplierDemo<S> withInitial(Supplier<? extends S> supplier) {
        // 获取supplier泛型类型
        //Type t = supplier.getClass().getGenericSuperclass();
        //if (t instanceof ParameterizedType) {
        //    System.out.println(t);
        //    for (Type type : ((ParameterizedType) t).getActualTypeArguments()) {
        //        System.out.println(type);
        //    }
        //}
        return new SupplierDemoFactory<>(supplier);
    }

    //工厂类（创建带有默认值的实例，supplier是提供默认值的函数）
    static final class SupplierDemoFactory<T> extends SupplierDemo<T> {
        private final Supplier<? extends T> supplier;

        SupplierDemoFactory(Supplier<? extends T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        @Override
        protected T initialValue() {
            return supplier.get();
        }
    }
}