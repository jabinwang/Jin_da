package com.jabin.da.base.set;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> linkedList;

    public LinkedListSet() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!linkedList.contains(e)) {
            linkedList.addFirst(e);
        }
    }

    @Override
    public boolean contain(E e) {
        return linkedList.contains(e);
    }

    @Override
    public void remove(E e) {
        linkedList.remove(e);
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        return "LinkedListSet{" +
                "linkedList=" + linkedList +
                '}';
    }

    public static void main(String[] args) {
        LinkedListSet<Integer> set = new LinkedListSet<>();
        set.add(11);
        set.add(11);
        set.add(22);
        set.add(33);
        set.add(44);

        System.out.println(set);

        set.remove(33); // [44, 66, 33, 22, 11]
        System.out.println(set);
    }
}
