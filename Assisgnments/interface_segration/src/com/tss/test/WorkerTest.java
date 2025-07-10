package com.tss.test;

import com.tss.model.IWorker;
import com.tss.model.Labour;
import com.tss.model.Robot;

public class WorkerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IWorker labour = new Labour();
		labour.drink();
		labour.eat();
		labour.startWork();
		labour.stopWork();
		
		IWorker robot = new Robot();
		robot.drink();
		robot.eat();
		robot.startWork();
		robot.stopWork();
		
	}

}
