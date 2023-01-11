package com.lewis.msemployee.entities.models;

import com.lewis.msemployee.entities.domain.Roles;

import java.util.List;

public class EmployeeModel {

    private String username;
    private Integer age;
    private String doc;
    private String email;
    private List<Roles> roles;

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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
