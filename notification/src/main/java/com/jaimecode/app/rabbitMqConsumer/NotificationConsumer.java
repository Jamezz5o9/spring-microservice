package com.jaimecode.app.rabbitMqConsumer;

import com.jaimecode.app.NotificationService;
import com.jaimecode.app.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

        @RabbitListener(queues = "${rabbitmq.queues.notification}")
        public void consumer(NotificationRequest notificationRequest){
        log.info("Consumed message: {}, from queue", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
