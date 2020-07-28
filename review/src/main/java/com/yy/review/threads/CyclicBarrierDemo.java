package com.yy.review.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

//cyclic	英[ˈsaɪklɪk]
//线程0准备
//线程2准备
//线程1准备
//准备完毕，开始比赛
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {

            @Override public void run() {
                System.out.println("准备完毕，开始比赛");
            }
        });
        for (int i = 0; i < 3; i++) {
            new Worker("线程" + i, cyclicBarrier).start();
            if (i == 1) {
                cyclicBarrier.reset();
            }
        }
    }

    static class Worker extends Thread {
        private final CyclicBarrier cyclicBarrier;

        Worker(String name, CyclicBarrier cyclicBarrier) {
            super(name);
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "准备");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "唤醒");
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + "异常唤醒");
                e.printStackTrace();
            }
        }
    }
}
