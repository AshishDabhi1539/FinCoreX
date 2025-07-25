package com.tss.tasks;

public class Caller implements Runnable {
    String msg;
    Callme target;
    public Thread t;

    public Caller(Callme targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        target.call(msg);
    }
}
