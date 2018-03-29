package use_multi_thread;

/**
 * 只要任意一个方法(synchronized修饰)或代码块（synchronized(this)修饰）被锁住，
 * 其他线程都无法访问所有被synchronized修饰的方法或被synchronized(this)修饰的代码块，
 * 但是还可以访问没有被synchronized同步的方法和代码块，或者synchronized(anythingElse)修饰的代码块。
 */
public class SyncLockObject {

    private String anyStr = "nonThis";

    public void otherMethod() {
        System.out.println("-----------------------run--otherMethod");
    }

    synchronized public void syncOtherMethod() {
        System.out.println("-----------------------run--sync--otherMethod");
    }

    public void syncBlockMethod() {
        synchronized (this) {
            System.out.println("-----------------------run--sync--block--Method");
        }
    }

    public void syncBlockNonThisMethod() {
        synchronized (anyStr) {
            System.out.println("-----------------------run--sync--block--nonthis--Method");
        }
    }

    synchronized public static void syncStaticMethod() {
        System.out.println("-----------------------run--sync--static--Method");
    }

    public void syncClassLockMethod() {
        synchronized (SyncLockObject.class) {
            System.out.println("-----------------------run--sync--class--lock--Method");
        }
    }

    public void doLongTimeTask() {
        synchronized (this) {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Synchronized threadName=" + Thread.currentThread().getName() + " i=" + (i+1));
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void doClassLongTimeTask() {
        synchronized (SyncLockObject.class) {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Synchronized threadName=" + Thread.currentThread().getName() + " i=" + (i+1));
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public class MyThread1 extends Thread {
        private SyncLockObject syncLockObject;

        public MyThread1(SyncLockObject syncLockObject) {
            super();
            this.syncLockObject = syncLockObject;
        }

        @Override
        public void run() {
            super.run();
//            syncLockObject.doLongTimeTask();
            syncLockObject.doClassLongTimeTask();

        }
    }

    public class MyThread2 extends Thread {
        private SyncLockObject syncLockObject;

        public MyThread2(SyncLockObject syncLockObject) {
            super();
            this.syncLockObject = syncLockObject;
        }

        @Override
        public void run() {
            super.run();
            syncLockObject.otherMethod();
        }
    }

    public class MyThread3 extends Thread {
        private SyncLockObject syncLockObject;

        public MyThread3(SyncLockObject syncLockObject) {
            super();
            this.syncLockObject = syncLockObject;
        }

        @Override
        public void run() {
            super.run();
            syncLockObject.syncOtherMethod();
        }
    }

    public class MyThread4 extends Thread {
        private SyncLockObject syncLockObject;

        public MyThread4(SyncLockObject syncLockObject) {
            super();
            this.syncLockObject = syncLockObject;
        }

        @Override
        public void run() {
            super.run();
            syncLockObject.syncBlockMethod();
        }
    }

    public class MyThread5 extends Thread {
        private SyncLockObject syncLockObject;

        public MyThread5(SyncLockObject syncLockObject) {
            super();
            this.syncLockObject = syncLockObject;
        }

        @Override
        public void run() {
            super.run();
            syncLockObject.syncBlockNonThisMethod();
        }
    }

    public class MyThread6 extends Thread {
        private SyncLockObject syncLockObject;

        public MyThread6(SyncLockObject syncLockObject) {
            super();
            this.syncLockObject = syncLockObject;
        }

        @Override
        public void run() {
            super.run();
            SyncLockObject.syncStaticMethod();
        }
    }

    public class MyThread7 extends Thread {
        private SyncLockObject syncLockObject;

        public MyThread7(SyncLockObject syncLockObject) {
            super();
            this.syncLockObject = syncLockObject;
        }

        @Override
        public void run() {
            super.run();
            syncLockObject.syncClassLockMethod();
        }
    }
}
