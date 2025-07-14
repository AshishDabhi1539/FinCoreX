package com.tss.behavioural.model;

public class SmsNotifier implements INotifier{

	@Override
	public void sendNotification(Account account, String message) {
		// TODO Auto-generated method stub
		System.out.println("Notification send to "+account.getName() +": " + message);
	}

}
