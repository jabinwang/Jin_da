package com.jabin.java;

import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable {
    // 当前拥有的票数
    private static int num = 10;
    ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            //1. 同步一
//            synchronized (this) {
//// 输出卖票信息
//                if (num > 0) {
//                    System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
//                }
//            }

            //2.同步二
//            printNum();
            //3.
//            printNum();
            //4.
            printNumWithLock();
        }
    }

    public static synchronized void printNum() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
        }
    }

    private void printNumWithLock() {
        lock.lock();
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
        }
        lock.unlock();
    }
}
