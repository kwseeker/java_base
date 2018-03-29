package use_multi_thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private Lock lock = new ReentrantLock();

    public void testMethod() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " " + (i+1));
        }
        lock.unlock();
    }

    public static class MyThread extends Thread {
        private ReentrantLockTest service;

        public MyThread(ReentrantLockTest reentrantLockTest){
            super();
            this.service = reentrantLockTest;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }
}
