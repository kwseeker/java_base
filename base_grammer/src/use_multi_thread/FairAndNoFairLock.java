package use_multi_thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 公平锁与非公平锁
 */
public class FairAndNoFairLock {

    private ReentrantLock lock;

    public FairAndNoFairLock(boolean isFair) {
        super();
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + "获得锁定");
        } finally {
            lock.unlock();
        }
    }

}
