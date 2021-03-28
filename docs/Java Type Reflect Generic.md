# Java 类型信息&反射&泛型

经常碰到一些使用了泛型的场景需要通过反射获取类型信息，针对不同实际类型做不同的处理。

+ 为何使用反射

+ 获取哪些信息
+ 反射怎么获取这些信息

## 基础

### 类型信息

很多场景需要在运行时识别对象和类信息。

获取上述信息的两种方式：`RTTI`、`反射`。

> `RTTI`：Run-Time Type Identification 运行时类型判定。
>
> `RTTI`的功能主要是由Class类实现的。可以通过对象的Class对象获取对象真正的所属类信息。不管类怎么向上（父类等泛化类型）转换，总能找到真正的Class类中定义的方法并执行，而不会迷失。这就是多态的实现原理。

#### 类型信息与反射

反射包：`java.lang.reflect`。

+ **Class**

  获取Class对象的三种方式：以`java.lang.String`为例。

  ```java
  Class clazz;
  clazz = String.class;
  clazz = "Arvin".getClass();
  clazz = Class.forName("java.lang.String");	//注意这个有“副作用”，如果类还没加载会加载
  ```

  常用方法与操作符：

  ```java
  getName()			//包含包路径的完整类名, eg:java.util.concurrent.ConcurrentHashMap
  getSimpleName()		//不包含包路径的类名，eg:ConcurrentHashMap
  getCanonicalName()	
  getPackage()		//返回包信息类
  getSupperClass()	//获取父类，注意接口虽然也继承Object,但是这里只能取到null
  getInterfaces()		//获取所有接口
  newInstance()		//类必须包含默认无参构造器
  isInterface()
  isEnum()
  isArray()
  isPrimitive()		//是否是基本类型
  cast()
  asSubClass()
  instanceof    
  //Filed
  getField()			//获取某个public的field(包括继承的)
  getDeclaredField()	//获取当前类的某个filed(不包括继承的)
  getFields()
  getDeclaredFields()
  
  isAssignableFrom()	//向上转型是否成立
  ```

+ **Field**

  获取类中字段的信息。

  常用方法

  ```java
  getName()			//获取filed名称
  getType()			//获取类型
  getModifiers()		//获取修饰符号
  get()
  set()
  
  ```

  > 通过反射访问Field要通过`SecurityManager`设置的规则。通过设置`setAccessible(true)`来访问非public字段。
  >
  > Modifier:
  >
  > ```
  > PUBLIC: 1
  > PRIVATE: 2
  > PROTECTED: 4
  > STATIC: 8
  > FINAL: 16
  > SYNCHRONIZED: 32
  > VOLATILE: 64
  > TRANSIENT: 128
  > NATIVE: 256
  > INTERFACE: 512
  > ABSTRACT: 1024
  > STRICT: 2048
  > ```

+ **Method**

  ```
  invoke()
  ```

+ **Constructor**

+ **Type**

  子类型包括`Class`、`ParameterizedType`、`GenericArrayType`、`WildcardType`。

  获取父类泛型类型(以`HashMap`为例)。

  ```java
  Type type = HashMap.class.getGenericSuperclass();
  if(type instanceof ParameterizedType) {
      ParameterizedType pt = (ParameterizedType) type;
      Type[] types = pt.getActualTypeArguments();
      Assert.assertEquals("K", types[0].getTypeName());
      Assert.assertEquals("V", types[1].getTypeName());
  }
  ```

+ **Array**

  该类提供动态地生成和访问 JAVA 数组的方法。

+ **Proxy**

  提供动态地生成代理类和类实例的静态方法。

### 动态代理



### 泛型

**泛型实现原理**：擦拭法（编码时指定的类型在编译之后被擦除，`<T>`被视作Object处理 ）。

> 擦拭法局限：
>
> T不能是基本数据类型；
> 不能取到带泛型的Class（如`List<String>`对这种对象`getClass()` 获取的都是`List.class`[没有类型信息]）；
> 无法判断带泛型的Class（还是因为没有类型信息）；
> 不能通过`new T()` 实例化 (还是因为没有类型信息)，需要借助`Class<T>`通过反射实例化。
> `instanceof` 不能判断带泛型的类型。

泛型只是在编译期做类型检查，运行时类型已被擦除，可以利用反射实现在运行时给指定类型的容器插入不同类型的值（反射操作没有类型检查）。

以`List`举例，其实对于`List<String>`和`List<Integer>`对象它们在运行时其实是相同的类型（运行时类型信息已会被擦除，即运行时无法获取泛型参数类型的信息，变为原生类型`List`, 只可以存储一切对象实例）。

#### 泛型方法

泛型方法并不依赖所处的类是泛型类。如果一个static的方法要访问泛型类的类型参数就必须使其称为泛型方法。

```java
//ThreadLocal创建带有默认值的ThreadLocal对象的静态方法
public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
    return new SuppliedThreadLocal<>(supplier);
}
```

#### 泛型数组

泛型数组不能使用`new`创建。

传参可以用可变参数传递`T[]`，即`T... args`。

```java
//Drink<String>[] drinks = new Drink<String>[2];    //错误写法
Drink<String>[] drinks = (Drink<String>[]) new Drink[2];	//类型为 Drink[].class
```



## 附录

### 参考书籍

《Java编程思想》

