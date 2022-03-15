package com.jabin.da.base;

import java.util.Arrays;

public class LoopQueue<E> {
    private E[] data;

    private int size;
    private int front;
    private int tail;//指向最后一个待放入数据的位置

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        size = 0;
        front = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return front == tail;
    }


    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(data.length << 1);
        }
        data[tail] = e;
        tail = (tail + 1)%data.length;
        size++;
    }

    public E dequeue() {
        E ret = data[front];
        data[front] = null;
        front = (front + 1) %data.length;
        size--;
        if (size == data.length >>1 ) {
            resize(data.length >> 1);
        }
        return ret;
    }



    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0 ; i < size; i++) {
            newData[i] = data[(i+front)%data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        return "LoopQueue{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", front=" + front +
                ", tail=" + tail +
                '}';
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>(10);
        for(int i = 0 ; i < 15 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
