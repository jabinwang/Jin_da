package com.jabin.da.base;

public class LinkedListQueue2<E> {

    private class Node{
        public  E e;
        public Node next;
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    '}';
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue2() {
        head = tail = null;
        size = 0;
    }


    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e, null);
            head = tail;
        }else {
            tail.next = new Node(e, null);
            tail = tail.next;
        }
        size++;
    }

    public E dequeue() {
        Node delNode = head;
        head = head.next;
        delNode.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return delNode.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        return res.toString();
    }


    public static void main(String[] args) {
        LinkedListQueue2<Integer> queue = new LinkedListQueue2<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
