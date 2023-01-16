package com.lewis.msuser.entities.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_plan")
public class Plan {

    @Id
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Date start;

    private Date finish;
    @OneToMany(mappedBy = "plan")
    private List<User> users;

    @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    private Category category;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
