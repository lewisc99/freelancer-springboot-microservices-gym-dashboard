package com.lewis.msemployee.entities.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tb_role")
public class Roles {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @NotNull(message = "Name could not be null")
    @Size(min=5, max = 30, message = "Name must have min 5 max 30")
    private String name;


    @ManyToMany(fetch = FetchType.LAZY,
    cascade =  {CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="employee_role",
            joinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="employee_id")
    )
    @JsonIgnore
    public List<Employee> employees;

    public Roles() {}

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
