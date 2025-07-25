package com.tss.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tss.tasks.MyTask;
import com.tss.tasks.MyTask2;

public class ExecutorServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ExecutorService service = Executors.newCachedThreadPool();
        //ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new MyTask());
        service.submit(new MyTask());        
        service.submit(new MyTask2());
        service.submit(new MyTask2());
        
        service.shutdown();

	}

}
