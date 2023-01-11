package com.lewis.msemployee.entities.models;

import com.lewis.msemployee.entities.domain.Roles;

import javax.validation.constraints.NotNull;
import java.util.List;

public class EmployeeModel {


    @NotNull
    private String username;
    @NotNull
    private Integer age;
    @NotNull
    private String doc;
    @NotNull
    private String email;

    private List<String> roles;

    public EmployeeModel() {
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
