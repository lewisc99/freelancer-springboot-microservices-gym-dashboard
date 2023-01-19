package com.lewis.msuser.entities.models;

import com.lewis.msuser.entities.domain.Status;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.util.Date;
import java.util.UUID;

public class PlanModel {
    @Nullable
    @Type(type = "uuid-char")
    private UUID id;
    @DateTimeFormat(pattern="yyyy/dd/MM")
    private Date start  ;
    @DateTimeFormat(pattern="yyyy/dd/MM")
    private Date finish;

    private Status status;
    private CategoryModel category;
    private UserModel user;

    @Nullable
    public UUID getId() {
        return id;
    }

    public void setId(@Nullable UUID id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
