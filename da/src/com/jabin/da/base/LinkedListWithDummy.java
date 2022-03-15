package com.jabin.da.base;

public class LinkedListWithDummy<E> {

    private  class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    '}';
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListWithDummy() {
        dummyHead = new Node();
        size = 0;
    }


    public void addFirst(E e) {
        add(0, e);
    }

    public void add(int index, E e) {
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    public E remove(int index) {
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node delNode = pre.next;
        pre.next = pre.next.next;
        size--;
        return delNode.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for(Node cur = dummyHead.next; cur != null ; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListWithDummy<Integer> list = new LinkedListWithDummy<>();
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
