package com.lewis.msuser.entities.dto;

import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;
import java.util.List;
import java.util.UUID;

public class CategoryDTO {

    @Nullable
    @Type(type = "uuid-char")
    private UUID id;
    private String name;
    private List<PlanDTO> plans;

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

    public List<PlanDTO> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanDTO> plans) {
        this.plans = plans;
    }
}
