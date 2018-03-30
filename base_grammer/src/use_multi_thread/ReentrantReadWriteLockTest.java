package use_multi_thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            try {
                lock.readLock().lock();
                System.out.println("获得读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(2000);
            } finally {
                lock.readLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            try {
                lock.writeLock().lock();
                System.out.println("获得写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(2000);
            } finally {
                lock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class MyThread extends Thread {
        private ReentrantReadWriteLockTest service;
        private String operateType;

        public MyThread(ReentrantReadWriteLockTest service, String operateType) {
            super();
            this.service = service;
            this.operateType = operateType;
        }

        @Override
        public void run() {
            switch (operateType) {
            case "write":
                service.write();
                break;
            case "read":
                service.read();
                break;
            default:
                System.out.println("错误的操作类型");
            }
        }
    }
}
