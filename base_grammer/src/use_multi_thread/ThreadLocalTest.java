package use_multi_thread;

import java.util.function.ToLongBiFunction;

public class ThreadLocalTest {

    public static class Tools {
        public static ThreadLocal t1 = new ThreadLocal();
    }

    public static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    Tools.t1.set("ThreadA " + (i+5));
                    System.out.println("ThreadA get Value=" + Tools.t1.get());
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    Tools.t1.set("ThreadB " + (i+7));
                    System.out.println("ThreadB get Value=" + Tools.t1.get());
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
