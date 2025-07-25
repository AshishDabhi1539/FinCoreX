package com.tss.tasks;

public class MyThread extends Thread {

	public MyThread(String name) {
		super(name);
	}

	public void run() {
		for (int i = 5; i >= 1; i--) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
