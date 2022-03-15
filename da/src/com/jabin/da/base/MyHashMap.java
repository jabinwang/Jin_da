package com.jabin.da.base;

//706
public class MyHashMap {

    private final Node[] nodes = new Node[10009];

    public MyHashMap() {

    }

    public void put(int key, int value) {
        int index = hash(key);
        Node head = nodes[index];
        if (head != null) {
            Node cur = head;
            while (cur != null) {
                if (cur.key == key) {
                    cur.value = value;
                    return;
                }
                cur = cur.next;
            }
        }
        Node node = new Node(key,value,head);
        nodes[index] = node;
    }

    public int get(int key) {
        int index = hash(key);
        Node head = nodes[index];
        if (head != null) {
            Node cur = head;
            while (cur != null) {
                if (cur.key == key) {
                    return cur.value;
                }
                cur = cur.next;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int index = hash(key);
        Node head = nodes[index];
        if (head != null) {
            Node prev = null;
            Node cur = head;
            while (cur != null) {
                if (cur.key == key) {
                    if (prev != null) {
                        prev.next = cur.next;
                    }else {
                        nodes[index] = head.next;
                    }
                    return;
                }
                prev = cur;
                cur = cur.next;
            }
        }
    }

    private int hash(int key) {
        int hash = Integer.hashCode(key);
        hash ^= (hash>>16);
        return hash % nodes.length;
    }

    private static class  Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
