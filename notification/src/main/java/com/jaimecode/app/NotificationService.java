package com.jaimecode.app;


import com.jaimecode.app.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    public void send(NotificationRequest notificationRequest){

            Notification notification = Notification.builder()
                    .localDateTime(LocalDateTime.now())
                    .message(notificationRequest.message())
                    .title("Welcome Notification!!!")
                    .customerId(notificationRequest.customerId())
                    .build();

            notificationRepository.save(notification);

    }
}
