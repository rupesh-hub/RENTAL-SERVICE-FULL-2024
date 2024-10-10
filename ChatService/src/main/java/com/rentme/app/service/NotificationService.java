package com.rentme.app.service;

import com.rentme.app.entity.Notification;
import com.rentme.app.entity.User;
import com.rentme.app.model.NotificationRequest;
import com.rentme.app.model.NotificationResponse;
import com.rentme.app.repository.NotificationRepository;
import com.rentme.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public void save(NotificationRequest request) {
        var notification = Notification
                .builder()
                .recipient(request.getRecipient())
                .action(request.getAction())
                .actionBy(request.getActionBy())
                .timestamp(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }

    public List<NotificationResponse> getNotifications(String username) {

        return Optional.ofNullable(notificationRepository.getNotifications(username))
                .orElse(Collections.emptyList())
                .stream()
                .map(notification -> {
                    // Fetch the user who performed the action (actionBy)
                    User actionByUser = userRepository.findByUsername(notification.getActionBy())
                            .orElseThrow(() -> new RuntimeException("User not found"));

                    return NotificationResponse.builder()
                            .actionByProfile(actionByUser.getProfile())
                            .actionBy(actionByUser.getFirstName() + " " + actionByUser.getLastName())
                            .id(notification.getId())
                            .action(notification.getAction())
                            .recipient(notification.getRecipient())
                            .timestamp(notification.getTimestamp())
                            .build();
                })
                .collect(Collectors.toList());
    }


}
