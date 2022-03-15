package com.jabin.da.base;

import org.w3c.dom.Node;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//哈希表+双向链表
//146
//https://github.com/doocs/leetcode/blob/main/solution/0100-0199/0146.Lru%20Cache/README.md
public class LRUCache {
    private class ListNode {
        private int key;
        private int value;
        private ListNode prev;
        private ListNode next;

        public ListNode(int key, int value, ListNode prev, ListNode next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public ListNode() {
            this(0, 0, null, null);
        }
    }

    private Map<Integer, ListNode> cache;
    private ListNode head;
    private ListNode tail;
    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        ListNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        ListNode node = cache.get(key);
        if (node == null) {
            ListNode newNode = new ListNode(key, value, null, null);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                ListNode delNode = removeTail();
                cache.remove(delNode.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void moveToHead(ListNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(ListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private ListNode removeTail() {
        ListNode delNode = tail.prev;
        removeNode(delNode);
        return delNode;
    }

}
