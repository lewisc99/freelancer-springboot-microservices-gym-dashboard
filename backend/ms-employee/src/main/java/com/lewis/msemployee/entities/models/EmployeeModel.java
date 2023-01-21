package com.lewis.msemployee.entities.models;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.List;

public class EmployeeModel {

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be empty")
    @Size(message = "Document must have min size 5 and Max size 20", min = 5, max = 20)
    private String username;
    @Min(value = 18, message = "Age must be at least 18")
    @Range(min=0, max=90)
    private Integer age;
    @NotNull(message = "Document cannot be null")
    @NotBlank(message = "doc cannot be empty")
    @Size(message = "Document must have min size 10 and Max size 20", min = 10, max = 20)
    private String doc;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Roles cannot be null")
    @Email(message = "Email should be valid")
    private List<String> roles;

    public EmployeeModel() {
    }

    public EmployeeModel(String username, Integer age, String doc, String email, List<String> roles) {
        this.username = username;
        this.age = age;
        this.doc = doc;
        this.email = email;
        this.roles = roles;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
