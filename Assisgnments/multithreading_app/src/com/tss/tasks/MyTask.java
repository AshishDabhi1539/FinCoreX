package com.tss.tasks;

public class MyTask implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 5; i >= 1; i--) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
