package com.lewis.msuser.entities.models;

import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class CategoryModel {
    @Nullable
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name  cannot be empty")
    @NotBlank(message = "name  cannot be empty")
    private String name;
    private List<PlanModel> plans;

    @Nullable
    public UUID getId() {
        return id;
    }

    public void setId(@Nullable UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlanModel> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanModel> plans) {
        this.plans = plans;
    }
}
