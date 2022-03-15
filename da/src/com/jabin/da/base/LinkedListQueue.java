package com.jabin.da.base;

public class LinkedListQueue<E> {

    private LinkedList<E> linkedList;


    public LinkedListQueue() {

        linkedList = new LinkedList<>();
    }

    public void enqueue(E e) {
        linkedList.addFirst(e);
    }

    public E dequeue() {
        return linkedList.removeLast();
    }

    @Override
    public String toString() {
        return "LinkedListQueue{" +
                "linkedList=" + linkedList +
                '}';
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
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
