package use_multi_thread;

//import java.util.ArrayList;

/**
 * 实现多线程法一：
 * 继承 Thread 类
 */
public class MyThread extends Thread {

//    private static final ArrayList<MyThread> myThreadList = new ArrayList<>();

    @Override
    public synchronized void start() {
        super.start();                      // 复写start方法，必须执行super.start();
        this.setName("myThread");
        System.out.println("启动MyThread线程实例");
    }

    /**
     * 新线程需要执行的任务写在这里
     */
    @Override
    public void run() {
//        super.run();
//        System.out.println("MyThread run() 正在执行");
        try {
            for (int i = 0; i < 5; i++) {
                int time = (int)(Math.random() * 1000);
                System.out.println("time: " + time);
                Thread.sleep(time);
                System.out.println("run = " + Thread.currentThread().getName());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
