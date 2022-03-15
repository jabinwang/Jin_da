package com.jabin.da.base;

import java.util.Arrays;

public class ArrayQueue<E> {

    private E[] data;
    private int size;

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public void enqueue(E e) {
        if (size == data.length) {
            resize(data.length << 1);
        }
        data[size++] = e;

    }

    public E dequeue() {
        E ret = data[0];
        for (int i =1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length/2) {
            resize(data.length >> 1);
        }
        return ret;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
