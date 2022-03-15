package com.jabin.da.base;

import javax.swing.plaf.IconUIResource;

class MyCircularQueue {

    int[] data;
    int head;
    int capacity;
    int size;
    public MyCircularQueue(int k) {
        this.capacity = k;
        data = new int[k];
        this.head= 0;
        this.size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        int tail = (head + size) % capacity;
        data[tail] = value;
        this.size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1)%capacity;
        this.size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int tail = (head + size - 1) % capacity;
        return data[tail];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
