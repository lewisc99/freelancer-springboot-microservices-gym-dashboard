package com.lewis.msemployee.entities.domain;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tb_role")
public class Roles {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String name;


    @ManyToMany(fetch = FetchType.LAZY,
    cascade =  {CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="employee_role",
            joinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="employee_id")
    )
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
