package com.jaimecode.app;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Builder
public class Notification {
    @Id
    private String id;
    private Integer customerId;
    private String message;
    private String title;
    private LocalDateTime localDateTime;
}
