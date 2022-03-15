package com.jabin.da.base;

import java.util.Arrays;

public class ArrayStack<E> {
    private E[] data;
    private int size;

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public void push(E e) {
        data[size++] = e;
    }

    public E pop() {
        E ret = data[size-1];
        size--;
        data[size] = null;
        return ret;
    }


    @Override
    public String toString() {
        return "ArrayStack{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(10);

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
