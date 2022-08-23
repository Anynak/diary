package com.anynak.diary.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.mapstruct.Mapping;

import javax.persistence.*;

@Data
@Entity
@SuperBuilder
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleName roleName;

    public Role(RoleName roleName) {
        this.roleName = roleName;

    }

    public Role() {
    }



}
