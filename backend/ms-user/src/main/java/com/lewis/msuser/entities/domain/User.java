package com.lewis.msuser.entities.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    public UUID id;

    public String name;

    public Integer age;

    public String doc;

    public String email;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    private  Plan plan;

    public User(){}


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
