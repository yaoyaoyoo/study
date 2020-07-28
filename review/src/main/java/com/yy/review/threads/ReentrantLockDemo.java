package com.yy.review.threads;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        new Worker1("线程1", reentrantLock).start();
        new Worker1("线程2", reentrantLock).start();
    }

    static class Worker1 extends Thread {

        private final ReentrantLock reentrantLock;
        Worker1(String name, ReentrantLock reentrantLock) {
            super(name);
            this.reentrantLock = reentrantLock;
        }

        @Override public void run() {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取锁");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        }
    }
}
