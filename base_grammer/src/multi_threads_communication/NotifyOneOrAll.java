package multi_threads_communication;

public class NotifyOneOrAll {

    /**
     * 线程等待
     * @param lock
     */
    public void waitOne(Object lock) {
        waitTimeOne(lock, 0);
    }

    /**
     * 超时自动唤醒
     * @param lock
     */
    public void waitTimeOne(Object lock, int ms) {
        try {
            synchronized (lock) {
                System.out.println("begin wait() ThreadName=" + Thread.currentThread().getName() + " expireTime=" + ms);
                lock.wait(ms);
                System.out.println("  end wait() ThreadName=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ThreadA extends Thread {
        private Object lock;

        public ThreadA(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            NotifyOneOrAll notifyOneOrAll = new NotifyOneOrAll();
            notifyOneOrAll.waitOne(lock);
        }
    }

    public static class ThreadB extends Thread {
        private Object lock;

        public ThreadB(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            NotifyOneOrAll notifyOneOrAll = new NotifyOneOrAll();
            notifyOneOrAll.waitOne(lock);
        }
    }

    public static class ThreadC extends Thread {
        private Object lock;

        public ThreadC(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            NotifyOneOrAll notifyOneOrAll = new NotifyOneOrAll();
            notifyOneOrAll.waitOne(lock);
        }
    }

    public static class NotifyThread extends Thread {
        private Object lock;

        public NotifyThread(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {   //这个值需要保持不变否则可能引发并行
                System.out.println("notify one ThreadName=" + Thread.currentThread().getName());
                lock.notify();
            }
        }
    }

    public static class NotifyAllThread extends Thread {
        private Object lock;

        public NotifyAllThread(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {   //这个值需要保持不变否则可能引发并行
                    System.out.println("notify all ThreadName=" + Thread.currentThread().getName());
                    lock.notifyAll();
            }
        }
    }
}
