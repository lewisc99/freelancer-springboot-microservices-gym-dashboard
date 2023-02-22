package com.lewis.msuser.entities.models;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class MessageModel {

    @NotNull(message = "user cannot be null")
    private UUID user;
    @NotNull(message = "subject cannot be null")
    private String subject;

    @NotNull(message = "text cannot be null")
    @NotBlank(message = "text  cannot be empty")
    private String text;

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
