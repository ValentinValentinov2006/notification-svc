package com.example.notification_svc.service;

import com.example.notification_svc.model.Notification;
import com.example.notification_svc.model.NotificationStatus;
import com.example.notification_svc.model.NotificationType;
import com.example.notification_svc.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class NotificationService {

    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(JavaMailSender mailSender, NotificationRepository notificationRepository) {
        this.mailSender = mailSender;
        this.notificationRepository = notificationRepository;
    }


    public void sendSimpleEmail(UUID userId, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        Notification notification = Notification.builder()
                .userId(userId)
                .body(body)
                .subject(subject)
                .createdOn(LocalDateTime.now())
                .type(NotificationType.EMAIL)
                .build();

        try {

            mailSender.send(message);
            notification.setStatus(NotificationStatus.SUCCEEDED);
            log.info("Email sent successfully to " + to);
        } catch (Exception e) {

            notification.setStatus(NotificationStatus.FAILED);
            log.error("Failed to send email to " + to, e);
        }


        notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
