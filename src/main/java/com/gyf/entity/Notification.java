package com.gyf.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String role;

    private String title;

    private String message;

    private String type;

    private Long referenceId;

    private String actionUrl;

    private Boolean isRead = false;

    private LocalDateTime createdDate;

    public Notification() {
    }

    public Notification(
            Long userId,
            String role,
            String title,
            String message,
            String type,
            Long referenceId,
            String actionUrl) {

        this.userId = userId;
        this.role = role;
        this.title = title;
        this.message = message;
        this.type = type;
        this.referenceId = referenceId;
        this.actionUrl = actionUrl;
        this.isRead = false;
        this.createdDate = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {

        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }

        if (isRead == null) {
            isRead = false;
        }

        if (role == null || role.trim().isEmpty()) {
            role = "USER";
        }
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