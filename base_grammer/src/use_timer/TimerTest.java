package use_timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    private static Timer timer = new Timer();

    static public class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务执行了！时间：" + new Date());
        }
    }

    public static Timer getTimer() {
        return timer;
    }

    public static void setTimer(Timer timer) {
        TimerTest.timer = timer;
    }
}
