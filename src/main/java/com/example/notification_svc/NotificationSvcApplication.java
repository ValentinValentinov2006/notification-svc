package com.example.notification_svc;

import com.example.notification_svc.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NotificationSvcApplication {

	/*@Autowired
	private NotificationService notificationService;*/

	public static void main(String[] args) {
		SpringApplication.run(NotificationSvcApplication.class, args);
	}
	/*@EventListener(ApplicationReadyEvent.class)
	public void sendEmail() {
          notificationService.sendSimpleEmail("valanval2006@gmail.com",
				  "This is an email subject", "This is an email body");
	}*/

}
