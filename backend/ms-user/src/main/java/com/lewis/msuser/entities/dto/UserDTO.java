package com.lewis.msuser.entities.dto;
import java.util.UUID;

public class UserDTO {

    public UUID id;
    public String username;
    public Integer age;
    public String doc;
    public String email;
    private PlanDTO plan;


    public UserDTO(){}


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

    public PlanDTO getPlan() {
        return plan;
    }
    public void setPlan(PlanDTO plan) {
        this.plan = plan;
    }
}
