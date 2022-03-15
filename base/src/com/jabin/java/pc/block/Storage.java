package com.jabin.java.pc.block;

import java.util.concurrent.LinkedBlockingDeque;

public class Storage {

    private final LinkedBlockingDeque<Object> list = new LinkedBlockingDeque<>(10);

    public void produce() {
        try {
            list.put(new Object());
            System.out.println("Producer " + Thread.currentThread().getName() + "produce one size is " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        try {
            list.take();
            System.out.println("Consumer " + Thread.currentThread().getName() + "consume one , now size is " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
