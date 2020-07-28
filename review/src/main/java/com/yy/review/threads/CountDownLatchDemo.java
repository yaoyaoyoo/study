package com.yy.review.threads;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Worker(countDownLatch).start();
        }
        new Worker2(countDownLatch).start();
        try {
            Thread.sleep(1000);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行完毕");
    }

    static class Worker extends Thread {
        private final CountDownLatch countDownLatch;

        Worker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("count - 1");
        }
    }

    static class Worker2 extends Thread {
        private final CountDownLatch countDownLatch;

        Worker2(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override public void run() {
            try {
                System.out.println("测试线程执行，进入等待");
                countDownLatch.await();
                System.out.println("获取到共享锁，开始睡眠----");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
