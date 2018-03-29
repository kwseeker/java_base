package use_multi_thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionWaitNotify {

    public static class MyService {
        private Lock lock = new ReentrantLock();        // 默认是非公平锁
        private Condition condition = lock.newCondition();
        // 如果需要单独唤醒，可以给不同的线程分配不同的Condition对象
        // private Condition condition1 = lock.newCondition();

        public void waitMethod() {
            try {
                lock.lock();
                System.out.println("A");
                condition.await();
                System.out.println("B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("锁释放了");
            }
        }

        public void signal() {
            try {
                lock.lock();
                System.out.println("signal时间：" + System.currentTimeMillis());
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadA extends Thread {
        private MyService service;
        public ThreadA(MyService service) {
            super();
            this.service = service;
        }
        @Override
        public void run() {
            service.waitMethod();
        }
    }
}
