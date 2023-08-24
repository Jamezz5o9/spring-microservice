package com.jaimecode.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.jaimecode.app"
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
                                        NotificationConfig notificationConfig){
        return args -> {

            producer.publish(
                    "foo",
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingkey()
            );
        };
    }
}
