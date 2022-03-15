package com.jabin.java.pc.sync;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Storage {
    private final LinkedList<Object> list = new LinkedList<>();

    public void produce() {
        synchronized (list) {
            while (list.size() + 1 > 10) {
                System.out.println("Producer" + Thread.currentThread().getName() + "full");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(new Object());
            System.out.println("Producer " + Thread.currentThread().getName() + "produce one , now size is " + list.size());
            list.notifyAll();

        }
    }

    public void consume() {
        synchronized (list) {
            while (list.size() == 0) {
                System.out.println("Consumer " + Thread.currentThread().getName() + "wait");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove();
            System.out.println("Consumer " + Thread.currentThread().getName() + "consume one , now size " + list.size());
            list.notifyAll();
        }
    }
}
