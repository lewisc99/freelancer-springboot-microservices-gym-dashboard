package com.lewis.msemployee.entities.domain;

import javax.persistence.*;
import java.util.List;
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


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "employee_role",
        joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns =  @JoinColumn(name =  "role_id"))
    private List<Roles> roles;

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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
