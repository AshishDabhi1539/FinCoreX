package com.tss.test;

import com.tss.tasks.MyThread;

public class ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Thread.currentThread());
		Thread.currentThread().setName("MAIN");
		System.out.println(Thread.currentThread());
		Thread.currentThread().setPriority(9);
		System.out.println(Thread.currentThread());
		
		MyThread thread1 = new MyThread("thread1");
		thread1.start();
		
		MyThread thread2 = new MyThread("thread2");
		thread2.start();
		
		MyThread thread3 = new MyThread("thread3");
		thread3.start();
		
		
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting");
	}

}
