package com.example.notification_svc.web;


import com.example.notification_svc.model.Notification;
import com.example.notification_svc.service.NotificationService;
import com.example.notification_svc.web.dto.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public String sendNotification(@RequestBody NotificationRequest request) {
        System.out.println("Sending email to: " + request.getEmail());
        System.out.println("Message: " + request.getMessage());

        notificationService.sendSimpleEmail(request.getUuid(),request.getEmail(), request.getMessage(), "Please log in your account CookMaster.");
        System.out.println(request.getUuid());
        return "Notification sent successfully";
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
}
