package com.tss.tasks;

public class MyTask2 implements Runnable{
	public void run() {
        for(int i = 3; i > 0; i--) {
            System.out.println("Implementing Threading Tasks");
        }
    }
}
