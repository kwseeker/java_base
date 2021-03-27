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

    //public static S SupplierDemo<S> withInitial(Supplier<? extends S> supplier) {
    //    return new SupplierDemo.SupplierFactory<>(supplier);
    //}
    public static T SupplierDemo<T> withInitial(Supplier<? extends T> supplier) {
        return new SupplierDemo.SupplierFactory<>(supplier);
    }

    //工厂类（supplier是工厂实例化方法）
    static final class SupplierFactory<T> extends SupplierDemo<T> {
        private final Supplier<? extends T> supplier;

        SupplierFactory(Supplier<? extends T> var1) {
            this.supplier = (Supplier)Objects.requireNonNull(var1);
        }

        protected T initialValue() {
            return this.supplier.get();
        }
    }
}