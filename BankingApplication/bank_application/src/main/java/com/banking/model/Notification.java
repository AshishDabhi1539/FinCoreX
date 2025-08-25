package com.banking.model;

import java.time.LocalDateTime;

public class Notification {
    private Long notificationId;
    private Long userId;
    private String title;
    private String message;
    private String type;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime readAt;
    private String priority;
    private String category;
    private String actionUrl;
    private boolean isRead;
    private String sender;
    private String recipient;
    private String channel;
    private String templateId;
    private String metadata;
    
    // Constructors
    public Notification() {}
    
    public Notification(Long userId, String title, String message, String type) {
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.type = type;
        this.status = "UNREAD";
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
        this.priority = "NORMAL";
    }
    
    public Notification(Long userId, String title, String message, String type, String priority) {
        this(userId, title, message, type);
        this.priority = priority;
    }
    
    // Getters and Setters
    public Long getNotificationId() {
        return notificationId;
    }
    
    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getReadAt() {
        return readAt;
    }
    
    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getActionUrl() {
        return actionUrl;
    }
    
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    
    public boolean isRead() {
        return isRead;
    }
    
    public void setRead(boolean read) {
        isRead = read;
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
    public String getChannel() {
        return channel;
    }
    
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
    public String getTemplateId() {
        return templateId;
    }
    
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    
    public String getMetadata() {
        return metadata;
    }
    
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
    
    // Business methods
    public boolean isUnread() {
        return "UNREAD".equalsIgnoreCase(status);
    }
    
    public boolean isReadStatus() {
        return "READ".equalsIgnoreCase(status);
    }
    
    public boolean isHighPriority() {
        return "HIGH".equalsIgnoreCase(priority);
    }
    
    public boolean isUrgent() {
        return "URGENT".equalsIgnoreCase(priority);
    }
    
    public void markAsRead() {
        this.status = "READ";
        this.isRead = true;
        this.readAt = LocalDateTime.now();
    }
    
    public void markAsUnread() {
        this.status = "UNREAD";
        this.isRead = false;
        this.readAt = null;
    }
    
    public boolean isTransactionNotification() {
        return "TRANSACTION".equalsIgnoreCase(type);
    }
    
    public boolean isSecurityNotification() {
        return "SECURITY".equalsIgnoreCase(type);
    }
    
    public boolean isAccountNotification() {
        return "ACCOUNT".equalsIgnoreCase(type);
    }
    
    public boolean isLoanNotification() {
        return "LOAN".equalsIgnoreCase(type);
    }
    
    public boolean isSystemNotification() {
        return "SYSTEM".equalsIgnoreCase(type);
    }
    
    public boolean isMarketingNotification() {
        return "MARKETING".equalsIgnoreCase(type);
    }
    
    public String getFormattedCreatedAt() {
        if (createdAt != null) {
            return createdAt.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }
        return "";
    }
    
    public String getFormattedReadAt() {
        if (readAt != null) {
            return readAt.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }
        return "";
    }
    
    public String getShortMessage() {
        if (message != null && message.length() > 100) {
            return message.substring(0, 97) + "...";
        }
        return message;
    }
    
    public String getPriorityIcon() {
        switch (priority.toUpperCase()) {
            case "URGENT":
                return "🔴";
            case "HIGH":
                return "🟠";
            case "NORMAL":
                return "🟡";
            case "LOW":
                return "🟢";
            default:
                return "⚪";
        }
    }
    
    public String getTypeIcon() {
        switch (type.toUpperCase()) {
            case "TRANSACTION":
                return "💰";
            case "SECURITY":
                return "🔒";
            case "ACCOUNT":
                return "🏦";
            case "LOAN":
                return "📋";
            case "SYSTEM":
                return "⚙️";
            case "MARKETING":
                return "📢";
            default:
                return "📌";
        }
    }
    
    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", isRead=" + isRead +
                '}';
    }
}

