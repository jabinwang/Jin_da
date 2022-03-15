package com.jabin.java.pc.cond;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private final LinkedList<Object> list = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();

    public void produce() {
        try {
            lock.lock();
            while (list.size() + 1 > 10) {
                System.out.println("Producer" + Thread.currentThread().getName() + "full");
                cond.await();
            }
            list.add(new Object());
            System.out.println("Producer " + Thread.currentThread().getName() + "produce one , now size is " + list.size());
            cond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        try {
            lock.lock();
            while (list.size() == 0) {
                System.out.println("Consumer " + Thread.currentThread().getName() + "wait");
                cond.await();
            }
            list.remove();
            System.out.println("Consumer " + Thread.currentThread().getName() + "consume one , now size " + list.size());
            cond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
