package com.lewis.msuser.entities.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tb_category")
public class Category {

    @Id
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Plan> plans;

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

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
}
