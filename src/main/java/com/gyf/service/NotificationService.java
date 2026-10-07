package com.gyf.service;

import java.util.List;

import com.gyf.dto.NotificationResponse;
import com.gyf.entity.Notification;

public interface NotificationService {

    Notification createNotification(
            Long userId,
            String title,
            String message,
            String type,
            Long referenceId,
            String actionUrl);

    void notifyAdmins(
            String title,
            String message,
            String type,
            Long referenceId,
            String actionUrl);

    void notifyUserByIdentity(
            String identity,
            String title,
            String message,
            String type,
            Long referenceId,
            String actionUrl);

    List<NotificationResponse> getNotifications(Long userId);

    long getUnreadCount(Long userId);

    NotificationResponse markAsRead(Long id);

    void markAllAsRead(Long userId);
}