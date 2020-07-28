package com.yy.review.threads;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    //线程0获得许可证
    //线程2获得许可证
    //线程1获得许可证
    //          测试线程获得许可证
    //线程3获得许可证
    //          测试线程释放许可证
    //线程4获得许可证
    //线程7获得许可证
    //线程6获得许可证
    //线程5获得许可证
    //线程8获得许可证
    //线程9获得许可证
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Worker("线程" + i, semaphore).start();
            if (i == 2) {
                new Worker2(" 测试线程", semaphore).start();
            }
        }
    }

    static class Worker extends Thread {
        private final Semaphore semaphore;

        Worker(String name, Semaphore semaphore) {
            super(name);
            this.semaphore = semaphore;
        }

        @Override public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "获得许可证");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + "释放许可证");

            }
        }
    }

    //semaphore 对释放许可证并未做合法性校验，这里直接release(10)导致许可证的数量在worker2读取到的数值之上加了10，
    //导致后续线程都获取到了许可证;
    static class Worker2 extends Thread {
        private final Semaphore semaphore;

        Worker2(String name, Semaphore semaphore) {
            super(name);
            this.semaphore = semaphore;
        }

        @Override public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "获得许可证");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(10);
                System.out.println(Thread.currentThread().getName() + "释放许可证");

            }
        }
    }

}
