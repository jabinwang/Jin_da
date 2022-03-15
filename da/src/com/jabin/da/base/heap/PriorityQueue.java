package com.jabin.da.base.heap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{


    private final MaxHeap<E> maxHeap;
    public PriorityQueue(int capacity) {
        maxHeap = new MaxHeap<>(capacity);
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void offer(E e) {
        maxHeap.add(e);
    }

    @Override
    public E poll() {
        return maxHeap.extractMax();
    }

    @Override
    public E peek() {
        return maxHeap.findMax();
    }
}
