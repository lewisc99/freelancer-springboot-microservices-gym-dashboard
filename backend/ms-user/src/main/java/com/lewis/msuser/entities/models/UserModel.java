package com.lewis.msuser.entities.models;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.UUID;

public class UserModel {
    
    @Nullable
    @Type(type = "uuid-char")
    public UUID id;
    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be empty")
    @Size(min = 5,max = 20, message = "Username must have minimum 10 and max 20 characters")
    public String username;
    @Min(value = 18, message = "Age must be at least 18")
    @Range(min=0, max=90)
    public Integer age;
    @NotNull(message = "Document cannot be null")
    @NotBlank(message = "doc cannot be empty")
    @Size(min = 10,max = 20, message = "Document must have minimum 10 and max 20 characters")
    public String doc;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    public String email;
    @NotNull(message = "Plan cannot be null")
    @Valid
    private PlanModel plan;
    public UserModel(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PlanModel getPlan() {
        return plan;
    }

    public void setPlan(PlanModel plan) {
        this.plan = plan;
    }
}
