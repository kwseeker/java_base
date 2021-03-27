# Java 函数式编程

Java 函数式编程是为了方便编写复杂的集合处理算法，能够并行处理批量数据。

面向对象是对数据进行抽象，函数式编程是对行为进行抽象。

函数式编程的优点：可读性高(响应地也更易于维护，可靠)，让事件处理系统更加简单(?)，更易于编写惰性代码（比如某个方法只有实际使用时才知道怎么写，可以传递一个函数变量，虽然借助匿名类和抽象类也可以实现，但是更加简洁）。



## Lambda表达式

### Lambda表达式都是静态类型的

Lambda 表达式中引用的局部变量必须是 final 或既成事实上的 final 变量(变量不可修改，引用的是值不是对象)。

> 闭包：一个函数和对其周围状态（**lexical environment，词法环境**）的引用捆绑在一起（或者说函数被引用包围），这样的组合就是**闭包**（**closure**）。也就是说，闭包让你可以在一个内层函数中访问到其外层函数的作用域。在 JavaScript 中，每当创建一个函数，闭包就会在函数创建的同时被创建出来。
>
> ```js
> function init() {
>     var name = "Mozilla"; // name 是一个被 init 创建的局部变量
>     function displayName() { // displayName() 是内部函数，一个闭包
>         alert(name); // 使用了父函数中声明的变量
>     }
>     displayName();
> }
> init();
> ```
>
> Java Lambda 并不能算严格意义的闭包。

### 函数接口

是实现`java`函数式编程的基石。

```java
// 接受一个对象返回一个boolean值
Predicate<T> T boolean 						示例：这张唱片已经发行了吗
Consumer<T> T void 								输出一个值
Function<T,R> T R 									获得 Artist 对象的名字
Supplier<T> None T 								工厂方法
UnaryOperator<T> T T 						 逻辑非 (!)
// 接受两个参数，返回一个值（参数和值类型相同）    
BinaryOperator<T> (T, T) T 				 求两个数的乘积 (*)
```

#### 类型推断

Lambda表达式可以通过上下文自动推断出表达式中所有参数类型。

#### 函数接口使用案例

`Java 8`为`ThreadLocal`拓展了`SuppliedThreadLocal`的静态内部类（protected），提供工厂方法，接受一个Lambda表达式并生成一个新的`ThreadLocal`对象。

> 提供了一种新的工厂

```java
static final class SuppliedThreadLocal<T> extends ThreadLocal<T> {
    private final Supplier<? extends T> supplier;

    SuppliedThreadLocal(Supplier<? extends T> var1) {
        this.supplier = (Supplier)Objects.requireNonNull(var1);
    }

    protected T initialValue() {
        return this.supplier.get();
    }
}
```









## 附录

### 参考资料

《Java 8 函数式编程》

《面向Java开发者的函数式编程》































