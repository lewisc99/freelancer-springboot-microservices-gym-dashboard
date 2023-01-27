package com.lewis.msuser.entities.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="tb_category")
@ApiModel(description="Category")
public class Category {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false,unique = true, columnDefinition = "BINARY(16)")
    @ColumnDefault("random_uuid()")
 //   @Type(type = "uuid-char")
    @ApiModelProperty(notes = "category Id type UUID")
    private UUID id;
    @ApiModelProperty(notes = "category Name is String", value = "String", name = "String" )
    private String name;

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
