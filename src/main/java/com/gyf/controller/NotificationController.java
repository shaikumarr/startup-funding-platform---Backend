package com.gyf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gyf.dto.NotificationResponse;
import com.gyf.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /*
     * GET
     * /api/notifications?userId=1
     */
    @GetMapping
    public List<NotificationResponse> getNotifications(
            @RequestParam Long userId) {

        return notificationService
                .getNotifications(userId);
    }

    /*
     * GET
     * /api/notifications/1
     */
    @GetMapping("/{userId}")
    public List<NotificationResponse> getNotificationsByUser(
            @PathVariable Long userId) {

        return notificationService
                .getNotifications(userId);
    }

    /*
     * GET
     * /api/notifications/unread-count/1
     */
    @GetMapping("/unread-count/{userId}")
    public long getUnreadCount(
            @PathVariable Long userId) {

        return notificationService
                .getUnreadCount(userId);
    }

    /*
     * PUT
     * /api/notifications/read/5
     */
    @PutMapping("/read/{id}")
    public NotificationResponse markRead(
            @PathVariable Long id) {

        return notificationService
                .markAsRead(id);
    }

    /*
     * PUT
     * /api/notifications/read-all/1
     */
    @PutMapping("/read-all/{userId}")
    public String markAllRead(
            @PathVariable Long userId) {

        notificationService
                .markAllAsRead(userId);

        return "All notifications marked as read";
    }
}