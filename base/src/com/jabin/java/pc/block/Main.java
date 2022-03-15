package com.jabin.java.pc.block;

import com.jabin.java.pc.block.Storage;

public class Main {

    public static void main(String[] args) {
        Storage storage = new Storage();

        Thread t1 = new Thread(new Producer(storage));
        Thread t2 = new Thread(new Producer(storage));
        Thread t3 = new Thread(new Producer(storage));

        Thread c1 = new Thread(new Consumer(storage));
        Thread c2 = new Thread(new Consumer(storage));
        Thread c3 = new Thread(new Consumer(storage));

        t1.start();
        t2.start();
        t3.start();

        c1.start();
        c2.start();
        c3.start();
    }


}
