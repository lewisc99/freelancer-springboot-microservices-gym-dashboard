package com.lewis.msemployee.entities.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Table(name = "tb_employee")
@Entity
public class Employee {

    @Id
    @Column(columnDefinition = "uuid",nullable = false)
    private UUID id;

    private String username;

    private Integer age;

    private String doc;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "employee_role",
        joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"), inverseJoinColumns =  @JoinColumn(name =  "role_id"))
    private List<Roles> roles =  new ArrayList<>();
    public Employee(){}

    public Employee(UUID id, String username, Integer age, String doc, String email, String password) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.doc = doc;
        this.email = email;
        this.password = password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

}
