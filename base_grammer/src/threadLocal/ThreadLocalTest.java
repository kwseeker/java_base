package threadLocal;

/**
 * Thread[main,5,main] test: @threadLocalVar=11 @sharedVar=4
 * Thread[Thread-0,5,main] test: @threadLocalVar=22 @sharedVar=4
 * 说明threadLocalVar在每个线程都有一个对象实例，而sharedVar是所有线程共享的
 */
public class ThreadLocalTest {

    //这个变量在每个线程中会有一个实例，多个线程的这个实例之间毫无关系
    private ThreadLocal<Integer> threadLocalVar = new ThreadLocal<>();
    //这个变量是所有线程共享的
    private Integer sharedVar = 1;

    private class LocalTestThread extends Thread {
        // ThreadLocal<String> mStringThreadLocal = new ThreadLocal<>();   //线程局部变量
        int localsSize = 12;
        ThreadLocal<String>[] mStringThreadLocals = (ThreadLocal<String>[]) new ThreadLocal[localsSize];
        ThreadLocal<String> childThreadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            super.run();

            for (int i = 0; i < localsSize; i++) {
                mStringThreadLocals[i] = new ThreadLocal<>();
                mStringThreadLocals[i].set("value" + i);
            }

            System.out.println(currentThread() + " ThreadLocals: ");
            for (int i = 0; i <localsSize ; i++) {
                System.out.println(mStringThreadLocals[i].get());
            }

            System.out.println("ThreadLocal initial value: " + childThreadLocal.get());  //获取ThreadLocal<String> childThreadLocal初始值

            childThreadLocal.set("TestValue");
            System.out.println(childThreadLocal.get());
            childThreadLocal.remove();  //清除 childThreadLocal.threadLocalHashCode & (len-1) 对应的 entry .
            //childThreadLocal.set("child thread string");
            System.out.println(childThreadLocal.get());

            // 共享性测试
            threadLocalVar.set(22);
            sharedVar *= 2;

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " test: @threadLocalVar=" + threadLocalVar.get()
                    + " @sharedVar=" + sharedVar);
        }
    }

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocalTest.new LocalTestThread().start();

        threadLocalTest.threadLocalVar.set(11);
        threadLocalTest.sharedVar *= 2;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        System.out.println(Thread.currentThread() + " test: @threadLocalVar=" + threadLocalTest.threadLocalVar.get()
                + " @sharedVar=" + threadLocalTest.sharedVar);
    }
}

/*
Thread[Thread-0,5,main] ThreadLocals:
value0
value1
value2
value3
value4
value5
value6
value7
value8
value9
value10
value11
ThreadLocal initial value: null
TestValue
null

Thread[main,5,main] test: @threadLocalVar=11 @sharedVar=4
Thread[Thread-0,5,main] test: @threadLocalVar=22 @sharedVar=4
*/