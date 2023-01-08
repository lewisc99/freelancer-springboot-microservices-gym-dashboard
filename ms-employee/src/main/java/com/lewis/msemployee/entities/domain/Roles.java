package com.lewis.msemployee.entities.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tb_role")
public class Roles {

    @Id
    @Column(columnDefinition = "uuid", unique = true)
    @NotNull
    private UUID id;

    @NotNull(message = "Name could not be null")
    @Size(min=5, max = 30, message = "Name must have min 5 max 30")
    private String name;


    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    public List<Employee> employees = new ArrayList<>();

    public Roles() {}

    public Roles(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

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



}
