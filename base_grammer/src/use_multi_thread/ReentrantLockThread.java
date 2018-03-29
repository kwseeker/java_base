package use_multi_thread;

public class ReentrantLockThread extends Thread {

    public int i = 10;

    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }

    synchronized public void operateI() {
        try {
            i--;
            System.out.println("ReentrantLockThread print i=" + i);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Service {
        synchronized public void service1() {
            System.out.println("service1");
            service2();
        }

        synchronized public void service2() {
            System.out.println("service2");
            service3();
        }

        synchronized public void service3() {
            System.out.println("service3");
        }
    }

    /**
     * 子类可重入调用父类的同步方法，
     */
    public class SubReentrantLockTread extends ReentrantLockThread {

        @Override
        public void run() {
            operateISub();
        }

        synchronized public void operateISub() {
            try {
                while( i > 0) {
                    i--;
                    System.out.println("SubReentrantLockThread.operateISub print i=" + i);
                    Thread.sleep(1000);
                    this.operateI();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
