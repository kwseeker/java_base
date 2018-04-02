package use_multi_thread2;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * 实现Callable接口通过FutureTask包装器来创建Thread
 *
 * 可以实现有返回值的线程
 *
 * 步骤：
 *  1. 创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且返回值。
 *  2. 创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值。
 *  3. 使用FutureTask对象作为Thread对象的target创建并启动新线程。
 *  4. 调用FutureTask对象的get方法来获得子线程执行结束后的返回值。
 */
public class CallableThread implements Callable<Integer> {      //泛型定义只支持类数据类型
    @Override
    public Integer call() throws Exception {
        Random rand = new Random();
        Thread.sleep(2000);     //模拟耗时
        int res = rand.nextInt(100);
        System.out.println("随机数: " + res);

        return res;
    }
}

