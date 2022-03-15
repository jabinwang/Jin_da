package com.jabin.da.base.set;

public class LinkedList<E> {
    private class Node{
        E data;
        Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + data +
                    '}';
        }

    }

    private Node dummyHead;
    private int size;


    public LinkedList() {
        this.dummyHead = new Node();
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(E e){
        dummyHead.next = new Node(e, dummyHead.next);
        size++;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.data.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public void remove(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.data.equals(e)) {
                break;
            }
            prev = prev.next;
        }
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = prev.next.next;
            delNode.next = null;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (Node cur = dummyHead.next; cur != null; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");

        return res.toString();
    }
}
