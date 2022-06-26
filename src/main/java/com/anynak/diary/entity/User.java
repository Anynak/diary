package com.anynak.diary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@Table(name="user")
public class User {
    public User() {

    }
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<DiaryPost> diaryPosts = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "user-role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        this.roles.add(role);
    }
    public void removeRole(Role role){
        this.roles.remove(role);
    }
}
