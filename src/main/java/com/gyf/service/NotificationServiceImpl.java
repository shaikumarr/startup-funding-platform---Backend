package com.gyf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyf.dto.NotificationResponse;
import com.gyf.entity.Notification;
import com.gyf.entity.User;
import com.gyf.repository.NotificationRepository;
import com.gyf.repository.UserRepository;

@Service
public class NotificationServiceImpl
        implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Notification createNotification(
            Long userId,
            String title,
            String message,
            String type,
            Long referenceId,
            String actionUrl) {

        if (userId == null) {
            return null;
        }

        String role = "USER";

        Optional<User> user =
                userRepository.findById(userId);

        if (user.isPresent()) {

            String userRole =
                    user.get().getRole();

            if (userRole != null &&
                    !userRole.trim().isEmpty()) {

                role =
                        userRole.trim().toUpperCase();
            }
        }

        Notification notification =
                new Notification(
                        userId,
                        role,
                        title,
                        message,
                        type,
                        referenceId,
                        actionUrl);

        return notificationRepository.save(
                notification);
    }

    @Override
    public void notifyAdmins(
            String title,
            String message,
            String type,
            Long referenceId,
            String actionUrl) {

        List<User> admins =
                userRepository
                        .findByRoleIgnoreCase("ADMIN");

        for (User admin : admins) {

            if (admin == null ||
                    admin.getId() == null) {
                continue;
            }

            createNotification(
                    admin.getId(),
                    title,
                    message,
                    type,
                    referenceId,
                    actionUrl);
        }
    }

    @Override
    public void notifyUserByIdentity(
            String identity,
            String title,
            String message,
            String type,
            Long referenceId,
            String actionUrl) {

        if (identity == null ||
                identity.trim().isEmpty()) {

            return;
        }

        Optional<User> user =
                userRepository
                        .findFirstByEmailIgnoreCase(
                                identity.trim());

        if (user.isEmpty()) {

            user =
                    userRepository
                            .findFirstByNameIgnoreCase(
                                    identity.trim());
        }

        if (user.isPresent() &&
                user.get().getId() != null) {

            createNotification(
                    user.get().getId(),
                    title,
                    message,
                    type,
                    referenceId,
                    actionUrl);
        }
    }

    @Override
    public List<NotificationResponse>
            getNotifications(Long userId) {

        return notificationRepository
                .findByUserIdOrderByCreatedDateDesc(
                        userId)
                .stream()
                .map(NotificationResponse::new)
                .toList();
    }

    @Override
    public long getUnreadCount(
            Long userId) {

        return notificationRepository
                .countByUserIdAndIsReadFalse(
                        userId);
    }

    @Override
    public NotificationResponse markAsRead(
            Long id) {

        Notification notification =
                notificationRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found"));

        notification.setIsRead(true);

        return new NotificationResponse(
                notificationRepository.save(
                        notification));
    }

    @Override
    public void markAllAsRead(
            Long userId) {

        List<Notification> notifications =
                notificationRepository
                        .findByUserIdOrderByCreatedDateDesc(
                                userId);

        for (Notification notification :
                notifications) {

            notification.setIsRead(true);
        }

        notificationRepository.saveAll(
                notifications);
    }
}