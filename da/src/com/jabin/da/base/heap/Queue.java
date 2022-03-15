package com.jabin.da.base.heap;

public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    void offer(E e);
    E poll();
    E peek();
}
