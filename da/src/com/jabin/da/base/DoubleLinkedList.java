package com.jabin.da.base;

public class DoubleLinkedList<E> {

    private class Node {
        E e;
        Node prev;
        Node next;

        public Node(Node prev, E e, Node next) {
            this.prev = prev;
            this.next = next;
            this.e = e;
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

    public DoubleLinkedList() {
        head = tail = null;
        size = 0;
    }

    public Node getNode(int index) {
        if (head == null) {
            return null;
        }
        Node cur;
        if (index < size >> 1) {
            cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
        } else {
            cur = tail;
            for (int i = size - 1; i > index; i--) {
                cur = cur.prev;
            }
        }
        return cur;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        if (index == size) {
            Node oldTail = tail;
            tail = new Node(oldTail, e, head);
            if (size == 0) {
                head = tail;
                head.next = head;
                head.prev = head;
            } else {
                oldTail.next = tail;
                head.prev = tail;
            }
        } else {
            Node prev = head.prev;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            Node addNode = new Node(prev, e, prev.next);
            prev.next.prev = addNode;
            prev.next = addNode;
            if (index == 0) {
                head = addNode;
            }
        }
        size++;
    }

    public E remove(int index) {
        Node cur = head;
        if (size == 1) {
            head = tail = null;
        } else {
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            Node prev = cur.prev;
            Node next = cur.next;
            prev.next = next;
            next.prev = prev;
            if (cur == head) {
                head = next;
            }
            if (cur == tail) {
                tail = prev;
            }
        }
        size--;
        return cur.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = head;
        for (int i = 0; i < size; i++) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addLast(11);
        list.addLast(22);
        list.addLast(33);
        list.addLast(44);

        list.add(0, 55); // [55, 44, 33, 22, 11]
        list.add(2, 66); // [55, 44, 66, 33, 22, 11]
        System.out.println(list);

        list.remove(0); // [44, 66, 33, 22, 11]
        list.remove(2); // [44 66, 22, 11]
        System.out.println(list);
    }
}
