package com.jabin.da.base.set;

public interface Set<E> {
    void add(E e);
    boolean contain(E e);
    void remove(E e);
    boolean isEmpty();
}
