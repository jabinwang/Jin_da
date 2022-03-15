package com.jabin.java.pc.sema;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private final LinkedList<Object> list = new LinkedList<>();
    private Semaphore notFull = new Semaphore(10);
    private Semaphore notEmpty = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public void produce() {
        try {
            notFull.acquire();
            mutex.acquire();
            list.add(new Object());
            System.out.println("Producer " + Thread.currentThread().getName() + "produce one , now size is " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           mutex.release();
           notEmpty.release();
        }
    }

    public void consume() {
        try {
            notEmpty.acquire();
            mutex.acquire();
            list.remove();
            System.out.println("Consumer " + Thread.currentThread().getName() + "consume one , now size " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notFull.release();
        }
    }

}
