package com.tss.behavioural.model;

public class EmailNotifier implements INotifier {
    @Override
    public void sendNotification(Account account, String message) {
        System.out.println("Email to " + account.getName() + ": " + message);
    }
}

