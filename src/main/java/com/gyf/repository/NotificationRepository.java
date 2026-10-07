package com.gyf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyf.entity.Notification;

@Repository
public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdOrderByCreatedDateDesc(
            Long userId);

    List<Notification> findByUserIdAndIsReadFalseOrderByCreatedDateDesc(
            Long userId);

    long countByUserIdAndIsReadFalse(Long userId);
}