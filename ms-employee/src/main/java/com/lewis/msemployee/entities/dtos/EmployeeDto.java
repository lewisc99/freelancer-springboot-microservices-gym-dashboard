package com.lewis.msemployee.entities.dtos;
import com.lewis.msemployee.entities.domain.Roles;
import java.util.List;
import java.util.UUID;

public class EmployeeDto {


    private UUID id;
    private String username;
    private Integer age;
    private String doc;
    private String email;
    private List<Roles> roles;

    public EmployeeDto() {
    }

    public EmployeeDto(UUID id, String username, Integer age, String doc, String email, List<Roles> roles) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.doc = doc;
        this.email = email;
        this.roles = roles;
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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
