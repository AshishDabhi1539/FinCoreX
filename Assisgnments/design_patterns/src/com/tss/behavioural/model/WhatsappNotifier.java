package com.tss.behavioural.model;

public class WhatsappNotifier implements INotifier {
    @Override
    public void sendNotification(Account account, String message) {
        System.out.println("WhatsApp to " + account.getName() + ": " + message);
    }
}
