package com.anynak.diary.entity;

import com.anynak.diary.RoleName;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Collection;

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
    private RoleName roleName = RoleName.ROLE_USER;

    public Role(RoleName roleName) {
        this.roleName = roleName;

    }
    public Role() { }
    @Override
    public String toString() {
        return roleName.toString();
    }
}
