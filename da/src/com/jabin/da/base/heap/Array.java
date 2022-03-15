package com.jabin.da.base.heap;

import java.util.Arrays;

public class Array<E> {

    private E[] data;
    private int size;
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public void addLast(E e) {
        data[size++] = e;
    }

    public void removeLast() {
        size--;
        data[size] = null;
    }

    public int getSize(){
        return size;
    }

    public E get(int index) {
        return data[index];
    }

    public void swap(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public String toString() {
        return "Array{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
