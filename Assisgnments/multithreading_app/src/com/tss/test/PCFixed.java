package com.tss.test;

import com.tss.tasks.Consumer;
import com.tss.tasks.Producer;
import com.tss.tasks.Q;

public class PCFixed {
    public static void main(String args[]) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
        System.out.println("Press Control-C to stop.");
    }
}

