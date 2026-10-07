package com.gyf.dto;

import java.time.LocalDateTime;

import com.gyf.entity.Notification;

public class NotificationResponse {

    private Long id;

    private Long userId;

    private String role;

    private String title;

    private String message;

    private String type;

    private Long referenceId;

    private String actionUrl;

    private Boolean isRead;

    private LocalDateTime createdDate;

    public NotificationResponse() {
    }

    public NotificationResponse(
            Notification notification) {

        this.id =
                notification.getId();

        this.userId =
                notification.getUserId();

        this.role =
                notification.getRole();

        this.title =
                notification.getTitle();

        this.message =
                notification.getMessage();

        this.type =
                notification.getType();

        this.referenceId =
                notification.getReferenceId();

        this.actionUrl =
                notification.getActionUrl();

        this.isRead =
                notification.getIsRead();

        this.createdDate =
                notification.getCreatedDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(
            LocalDateTime createdDate) {

        this.createdDate = createdDate;
    }
}