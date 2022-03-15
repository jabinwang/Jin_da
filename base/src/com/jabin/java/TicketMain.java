package com.jabin.java;

public class TicketMain {

    public static void main(String[] args) {
        Ticket t = new Ticket();//创建一个线程任务对象。
        Ticket ti2 = new Ticket();//创建一个线程任务对象。
//创建4个线程同时卖票
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(ti2);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(ti2);
//启动线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
