package com.anynak.diary.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Long userId;

    @Column(name="login")
    private String login;

    @Column(name="password_hash")
    private String passwordHash;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<DiaryPost> diaryPosts = new ArrayList<>();
}
