package com.lewis.msuser.entities.dto;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.util.Date;
import java.util.UUID;

public class PlanDTO {
    @Nullable
    @Type(type = "uuid-char")
    private UUID id;
    @DateTimeFormat(pattern="yyyy/dd/MM")
    private Date start;
    @DateTimeFormat(pattern="yyyy/dd/MM")
    private Date finish;
    private CategoryDTO category;
    private UserDTO user;

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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
