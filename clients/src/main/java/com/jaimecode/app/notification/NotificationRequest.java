package com.jaimecode.app.notification;

public record NotificationRequest(Integer customerId,
                                  String customerEmail,
                                  String message) {
}
