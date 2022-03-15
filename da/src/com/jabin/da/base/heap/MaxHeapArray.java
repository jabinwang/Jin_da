package com.jabin.da.base.heap;

import java.util.Random;

public class MaxHeapArray<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeapArray(int capacity) {
        data = new Array<>(capacity);
    }

    private int parent(int index) {
        if (index == 0) {
            return -1;
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public void add(E e) {
        data.addLast(e);
        shiftUp(data.getSize() - 1);
    }

    public E extractMax() {
        E ret = data.get(0);
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void shiftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(parent(k), k);
            k = parent(k);
        }
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < data.getSize()
                    && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }
            if (data.get(k).compareTo(data.get(j)) > 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "data=" + data +
                '}';
    }

    public static void main(String[] args) {
        int n = 10;

        MaxHeapArray<Integer> maxHeap = new MaxHeapArray<>(11);
        Random random = new Random();
        for (int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(30));
        System.out.println(maxHeap);
        ;
        for (int i = 0; i < n; i++) {
            System.out.println(maxHeap.extractMax());
        }
    }
}
