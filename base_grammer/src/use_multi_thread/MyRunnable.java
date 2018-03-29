package use_multi_thread;

/**
 * 实现多线程法二：
 * 实现 Runnable 接口
 */
public class MyRunnable implements Runnable {

    private static int shareVar = 5;    // 共享数据
    private int var = 5;                // 非共享数据，每个实例一份
    @Override
    synchronized public void run() {
        shareVar--;
        var--;
        System.out.println("MyRunnable运行中, shareVar=" + shareVar + " var=" + var);
    }

}
