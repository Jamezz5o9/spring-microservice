package com.jaimecode.app;

public record CustomerRegistrationRequest(String firstName,
                                          String lastName,
                                          String email) {
}
