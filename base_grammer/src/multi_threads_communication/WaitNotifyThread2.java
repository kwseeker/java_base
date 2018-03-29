package multi_threads_communication;

public class WaitNotifyThread2 extends Thread {

    private Object lock;

    public WaitNotifyThread2(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("开始 notify time=" + System.currentTimeMillis());
            lock.notify();
            System.out.println("结束 notify time=" + System.currentTimeMillis());
        }
    }
}
