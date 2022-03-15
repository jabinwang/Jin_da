package com.jabin.da.base;

//705
public class MyHashSet {
    private Node[] nodes = new Node[10009];
    public MyHashSet() {

    }

    public void add(int key) {
        int index = hash(key);
        Node head = nodes[index];
        if (head != null) {
            Node cur = head;
            while (cur != null) {
                if (cur.key == key) {
                    return;
                }
                cur = cur.next;
            }

        }
        Node node = new Node(key, head);
        nodes[index] = node;

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

    public boolean contains(int key) {
        int index = hash(key);
        Node head = nodes[index];
        if (head != null) {
            Node cur = head;
            while (cur != null) {
                if (cur.key == key) {
                    return true;
                }
                cur = cur.next;
            }
        }
        return false;

    }

    private int hash(int key) {
        int hash = Integer.hashCode(key);
        hash ^= (hash >> 16);
        return hash % nodes.length;
    }

    private static class Node {
        int key;
        Node next;

        public Node(int key, Node next) {
            this.key = key;
            this.next = next;
        }
    }
}
