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
Predicate<T> T boolean							示例：这张唱片已经发行了吗
// 一个入参，无返回值
Consumer<T> T void								示例：输入一个值
// 两个入参，无返回值
BiConsumer<T, U> void   					示例：Collectors.to
// 输入T返回R
Function<T,R> T R									示例：获得 Artist 对象的名字
BiFunction<T, U, R>  (T, U) R
// 接受两个参数，返回一个值（参数和值类型相同）    
BinaryOperator<T> (T, T) T 				 	示例：合并两个List容器实例
// 无入参，返回一个对象
Supplier<T> None T								示例: Collectors.toList中 (Supplier<List<T>> ArrayList::new)
// 输入T返回T
UnaryOperator<T> T T						示例：逻辑非 (!)
```

> `Bi`和`Binary`都是“双”的意思，

+ `Supplier`

  ```java
  @FunctionalInterface
  public interface Supplier<T> {
      T get();
  }
  ```

+ `BiComsumer`

  ```java
  void accept(T t, U u);
  ```

  示例：

  ```java
  List<Integer> intList = new ArrayList<>();
  // biConsumer1其实是生成的匿名类，实现了accept()方法
  BiConsumer<List<Integer>, Integer> biConsumer1 = List::add;
  //BiConsumer<List<Integer>, Integer> biConsumer1 = (list, item) -> list.add(item);
  BiConsumer<List<Integer>, Integer> biConsumer2 = new BiConsumer<List<Integer>, Integer>() {
      @Override
      public void accept(List<Integer> integers, Integer integer) {
          integers.add(integer);
      }
  };
  biConsumer1.accept(intList, 1);
  biConsumer2.accept(intList, 2);
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

## 流

容器`for`语法糖是外部迭代（本质是借助迭代器实现），`stream`是内部迭代(？)。

### 常用操作

+ **`collect()`**
+ **`map()`** 
+ **`filter()`**

+ **`flatMap()`**

  将多个流合并为一个流

+ **`max()`**
+ **`min()`**

+ **`reduce()`**

  从一组值中生成一个值，count() max() min() 都是通过reduce()实现的。

  ```java
  /**
   * identity 是基准值（首次计算作为计算结果代入），accumulator累加器（将之前的计算结果和新元素进行计算处理的算法）
   * 等同于
   * T result = identity;
   * for (T element : this stream)
   *     result = accumulator.apply(result, element)
   * return result;
  */   
  T reduce(T identity, BinaryOperator<T> accumulator);
  // reduce实现累加器
  // 0 作为累加器的初始值，acc保存当前累加器累加结果，element是当前元素
  int count = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
  ```

### 实现原理

很多框架源码越来越多地使用起了函数式接口，分析一段完整的实现将对阅读框架源码很有帮助。

Lambda表达式被`JVM`用来**生成了一个内部类**，然后这个内部类实现了函数式接口。

```java
List<Integer> filteredList =  list.stream().filter(val -> val > 2).collect(Collectors.toList());
// 1 stream() 创建一个流 
// 第一个参数是用于遍历和分组的并行迭代遍历器，第二个参数是是否并行执行
// 关于spliterator，源码说使用集合的iterator()接口获取元素。Java1.8的集合类都默认实现了这个类，对于ArrayList是ArrayListSpliterator
StreamSupport.stream(spliterator(), false);	//Collection.java
// 1.1 第一个参数：当前集合对象（list）,第二个参数：特征值
// 它的作用就是为了拆分数据用于遍历，细节不说了随便找个文件看看：https://blog.csdn.net/lh513828570/article/details/56673804
Spliterators.spliterator(this, 0);
// 流就是一个管道
// 之前代码中用到方法（如：filter()、findFirst()、reduce()、collect()、sorted()）是在ReferencePipeline定义的
new ReferencePipeline.Head<>(spliterator,
                             StreamOpFlag.fromCharacteristics(spliterator),
                             parallel);
// 源码注释说Head是ReferencePipeline的源阶段（第一个阶段，流的处理分为多个阶段）
// Head也是ReferencePipeline类型，说明流这个过程就是一串ReferencePipeline调用
// 链表关系在AbstractPipeline的nextStage中保持，个人推断filter()方法本质就是建新节点将引用赋值给nextStage
static class Head<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {}
// 2 filter()
// 经过调试发现filter()返回的新的Stream对象引用确实赋值给了nextStage
return new StatelessOp<P_OUT, P_OUT>(this, StreamShape.REFERENCE,
                                     StreamOpFlag.NOT_SIZED) {
    // 关于这个方法是做什么的执行流程里面分析
    @Override
    Sink<P_OUT> opWrapSink(int flags, Sink<P_OUT> sink) {
        return new Sink.ChainedReference<P_OUT, P_OUT>(sink) {
            @Override
            public void begin(long size) {
                downstream.begin(-1);
            }

            @Override
            public void accept(P_OUT u) {
                // lambda 表达式执行
                if (predicate.test(u))
                    downstream.accept(u);
            }
        };
    }
};
// AbstractPipeline 构造函数中实现nextStage赋值
previousStage.nextStage = this;
// 3 collect()
// 真正执行流式处理
// 3.1 创建Collector容器
Collectors.toList()
// 3.2 创建ReduceOp操作，先跳过
   
// 3.3 collect() 真正开始执行的位置，此例子中this是filter()返回的ReferencePipeline节点，后者先查看下头节点(stream返回的ReferencePipeline节点)是否有并行迭代遍历器Spliterator。然后把头节点的sourceSpliterator引用清空？
terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
// helper 是stream最后一个ReferencePipeline节点
// 
helper.wrapAndCopyInto(makeSink(), spliterator).get();
// makeSink()

// opWrapSink()
copyInto(wrapSink(Objects.requireNonNull(sink)), spliterator);

// 
```

`Collector`接口: 将流元素保存到容器中，提供了3个函数式接口参数：

```
Supplier:						保存到容器首先要有一个容器，实例化容器实例
BiConsumer 				  有容器后要考虑怎么把流元素放到容器实例中, 指定容器存储元素的方法
BinaryOperator　     容器合并操作，估计是在并行处理的时候才会用到
```

`TerminalOp`接口

定义流处理操作，将一个stream作为输入，生成一个结果或副产品。

```java
makeSink()						  //
inputShape()					//获取StreamShape
evaluateSequential() 	//串行计算
evaluateParallel()			//并行计算
```



```java
// 实现类
FindOp in FindOps
ForEachOp in ForEachOps
MatchOp in MatchOps
OfDouble in ForEachOp in ForEachOps
OfInt in ForEachOp in ForEachOps
OfLong in ForEachOp in ForEachOps
OfRef in ForEachOp in ForEachOps

ReduceOp in ReduceOps
```

`Sink`



```
begin
end
cancellationRequested
accept
accept
accept
accept
andThen
OfInt
OfLong
OfDouble
ChainedReference
ChainedInt
ChainedLong
ChainedDouble
```



`StreamShape`

定义流及内部数据的类型？对象类型 or int or long or double。

`StreamOpFlag`



## 附录

### 参考资料

《Java 8 函数式编程》

《面向Java开发者的函数式编程》































