package com.jabin.da.base.map;

public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node{
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node() {
            this(null, null, null);
        }
    }

    private final Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        this.size = 0;
    }

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (key.equals(cur.key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }else {
            node.value = value;
        }
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node != null) {
            node.value = newValue;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }
}
