package com.anynak.diary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data
@RequiredArgsConstructor
@Entity
@SuperBuilder
@Table(name="user")
public class User {

    public User(String login, String passwordHash) {
        this.login=login;
        this.passwordHash=passwordHash;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    @NonNull
    private Long userId;

    @Column(name="login")
    @NonNull
    private String login;

    @Column(name="email")
    private String email;

    @Column(name="password_hash")
    private String passwordHash;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<DiaryPost> diaryPosts = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    public User() {

    }

    public void addRole(Role role){
        if(roles==null){
            roles = new HashSet<>();
        }
        this.roles.add(role);
    }
    public void removeRole(Role role){
        this.roles.remove(role);
    }
}
