package com.lewis.msemployee.entities.domain;

import javax.persistence.*;
import java.util.UUID;


@Table(name = "tb_employee")
@Entity
public class Employee {


    @Id
    private UUID id;
    private Integer age;
    private String doc;
    private String email;
    private String password;

    public Employee(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
