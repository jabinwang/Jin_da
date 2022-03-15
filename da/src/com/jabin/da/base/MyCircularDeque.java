package com.jabin.da.base;

public class MyCircularDeque {

    private int[] data;
    private int head;
    private int size;
    private int capacity;

    public MyCircularDeque(int k) {
        this.capacity = k;
        data = new int[k];
        this.head = 0;
        this.size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        if (!isEmpty()){
            head = (head -1 + capacity) % capacity;
        }
        data[head] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        int tail = (head + size) % capacity;
        data[tail] = value;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        int tail = (head + size -1) % capacity;
        return data[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
