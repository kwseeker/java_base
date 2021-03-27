package top.kwseeker.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.function.Supplier;

public class LambdaTest {

    /**
     * Lambda应用的是值不是变量
     */
    @Test
    public void testReference() {
        // 1 抽象类写法：对主类方法中变量的引用
        final String name = "Arvin";
        //String name = "Arvin";          // 这里替换成 non final 看看会怎么样？编译器会报错：Variable 'name' is accessed from within inner class, needs to be declared final
        new SomeAction() {
            @Override
            public void doAction() {
                System.out.println(name);
            }
        }.doAction();

        // 2　Lambda写法
        String name1 = "Lee";
        //name1 = "bob";                                             // 但是不能改变量的值否则还是会报 Variable used in lambda expression should be final or effectively final
        Player player = new Player(() -> System.out.println(name1));    // non final 但是用Lambda就没问题
        player.play();
    }

    @Test
    public void testSuppliedFactory() {
        SupplierDemo<String> supplierDemo = SupplierDemo.withInitial(() -> "Hello");
        String ret = supplierDemo.get();
        Assert.assertEquals(ret, "Hello");
    }

    /**
     *
     * @param <T>
     */
    //public static class SupplierDemo<T> {
    //
    //    private T value;
    //
    //    public T get() {
    //        if(value != null) {
    //            return value;
    //        }
    //        return initialValue();
    //    }
    //
    //    protected T initialValue() {
    //        return null;
    //    }
    //
    //    public static T SupplierDemo<T> withInitial(Supplier<? extends T> supplier) {
    //        return new SupplierDemo.SupplierFactory(supplier);
    //    }
    //
    //    //工厂类（supplier是工厂实例化方法）
    //    static final class SupplierFactory<T> extends SupplierDemo<T> {
    //        private final Supplier<? extends T> supplier;
    //
    //        SupplierFactory(Supplier<? extends T> var1) {
    //            this.supplier = Objects.requireNonNull(var1);
    //        }
    //
    //        protected T initialValue() {
    //            return this.supplier.get();
    //        }
    //    }
    //}

    private static class Player {
        SomeAction action;

        public Player(SomeAction action) {
            this.action = action;
        }

        public void play() {
            action.doAction();
        }
    }

    public interface SomeAction {
        void doAction();
    }
}
