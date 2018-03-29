package multi_threads_communication;

public class WaitNotifyThread1 extends Thread {

    private Object lock;

    public WaitNotifyThread1(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("开始 wait time=" + System.currentTimeMillis());
                lock.wait();
                System.out.println("结束 wait time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
