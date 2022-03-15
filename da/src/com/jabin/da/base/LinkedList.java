package com.jabin.da.base;

public class LinkedList<E> {

    private  class Node {
        public E e;
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
    private int size;
    private Node tail;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(E e) {
        head = new Node(e, head);
        size++;
    }

    public void addCycle(int index, E e){
        if (index == 0) {
            head = new Node(e, head);
            if (size == 0) {
                tail = head;
            }
            tail.next = head;
        }else {
            Node prev = head;
            for (int i = 0; i <index-1; i++) {
                prev = prev.next;
            }
            prev.next = new Node(e, prev.next);
        }
        size++;
    }

    public void add(int index, E e) {
        if (index == 0) {
            head = new Node(e, head);
            size++;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            prev.next = new Node(e, prev.next);
            size++;
        }
    }

    public E remove(int index) {
        Node delNode = head;
        if (index == 0) {
            head = head.next;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            delNode = prev.next;
            prev.next = prev.next.next;
        }
        size--;
        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (Node cur = head; cur != null; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(11);
        list.addFirst(22);
        list.addFirst(33);
        list.addFirst(44);

        list.add(0, 55); // [55, 44, 33, 22, 11]
        list.add(2, 66); // [55, 44, 66, 33, 22, 11]
        System.out.println(list);

        list.remove(0); // [44, 66, 33, 22, 11]
        list.remove(2); // [44 66, 22, 11]
        System.out.println(list);
    }
}
