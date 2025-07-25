package com.tss.tasks;

import java.util.concurrent.Callable;

public class NewTask implements Callable<Integer>{

	 public Integer call() throws Exception {
	        return (int)(Math.random() * 100);
	    }
}
