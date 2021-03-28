package top.kwseeker.generic;

/**
 * 泛型实现元组，用于方法返回多个对象
 */
public class Tuple<A, B> {
    public final A first;
    public final B second;

    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "first=" + first.toString() +
                ", second=" + second.toString() +
                '}';
    }
}

// 还可以通过下面方式进行拓展
class ThreeTuple<A, B, C> extends Tuple<A, B> {
    public final C three;

    public ThreeTuple(A first, B second, C three) {
        super(first, second);
        this.three = three;
    }

    @Override
    public String toString() {
        return "ThreeTuple{" +
                "first=" + first.toString() +
                ", second=" + second.toString() +
                ", three=" + three.toString() +
                '}';
    }
}

