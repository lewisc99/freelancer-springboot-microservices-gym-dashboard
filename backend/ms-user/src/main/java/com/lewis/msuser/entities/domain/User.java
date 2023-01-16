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

    public String password;

    public User(){}



}
