package com.jabin.da.base.heap;

public class MaxHeap<E extends Comparable<E>> {

    private final E[] data;
    private int size;

    public MaxHeap(int capacity) {
        data = (E[]) new Comparable[capacity];
        size = 0;
    }

    public void add(E e) {
        data[size++] = e;
        shiftUp(size - 1);
    }

    private void shiftUp(int k) {
        while (k > 0) {
            int parent = (k-1)/2;
            if (data[k].compareTo(data[parent]) > 0) {
                swap(k, parent);
            }
            k = parent;
        }
    }

    private void swap(int i, int j) {
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public E findMax() {
        return data[0];
    }

    public E extractMax() {
        E ret = data[0];
        swap(0, size-1);
        size--;
        shiftDown(0);
        return ret;

    }

    public E replace(E e) {
        E ret = findMax();
        data[0] = e;
        shiftDown(0);
        return ret;
    }

    private void shiftDown(int k) {
        while (2*k + 1 < size) {
            int j = 2*k + 1;
            if (j + 1 < size && data[j].compareTo(data[j+1]) < 0) {
                j++;
            }
            if (data[k].compareTo(data[j]) >= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize(){
        return size;
    }
}
