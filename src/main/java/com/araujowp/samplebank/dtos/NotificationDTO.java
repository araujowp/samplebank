package com.araujowp.samplebank.dtos;

public class NotificationDTO {

	private final String email;
    private final String message;

    public NotificationDTO(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}
