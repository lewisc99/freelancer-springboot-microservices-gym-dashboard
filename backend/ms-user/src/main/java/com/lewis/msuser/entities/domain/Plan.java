package com.lewis.msuser.entities.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
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
}
