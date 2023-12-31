package com.jaimecode.app;

import com.jaimecode.app.fraud.FraudCheckResponse;
import com.jaimecode.app.fraud.FraudClient;
import com.jaimecode.app.notification.NotificationClient;
import com.jaimecode.app.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        assert fraudCheckResponse != null;

       if(fraudCheckResponse.isFraudster()){
           throw new IllegalArgumentException("Fraudster");
       }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, Welcome to Jaimecode center....", customer.getFirstName())
        );

       log.info("This is the first stage ---------> {}", notificationRequest);

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        log.info("This is the second stage ---------> {}", notificationRequest);

    }
}
