package com.anynak.diary.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

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

    @Column(name = "role_name")
    private String roleName;
    public Role(String roleName) {
        this.roleName = roleName;

    }
    public Role() {

    }
}
