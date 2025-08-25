package com.banking.model;

public class NotificationPreference {
    private long preferenceId;   // Primary key
    private long userId;         // Foreign key referencing users table
    private boolean emailEnabled;
    private boolean smsEnabled;
    private boolean whatsappEnabled;

    public NotificationPreference() {}

    public NotificationPreference( long userId, boolean emailEnabled, boolean smsEnabled, boolean whatsappEnabled) {
        
        this.userId = userId;
        this.emailEnabled = emailEnabled;
        this.smsEnabled = smsEnabled;
        this.whatsappEnabled = whatsappEnabled;
    }

    

	public long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public void setEmailEnabled(boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    public boolean isSmsEnabled() {
        return smsEnabled;
    }

    public void setSmsEnabled(boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
    }

    public boolean isWhatsappEnabled() {
        return whatsappEnabled;
    }

    public void setWhatsappEnabled(boolean whatsappEnabled) {
        this.whatsappEnabled = whatsappEnabled;
    }

    @Override
    public String toString() {
        return "NotificationPreference{" +
                "preferenceId=" + preferenceId +
                ", userId=" + userId +
                ", emailEnabled=" + emailEnabled +
                ", smsEnabled=" + smsEnabled +
                ", whatsappEnabled=" + whatsappEnabled +
                '}';
    }
}
