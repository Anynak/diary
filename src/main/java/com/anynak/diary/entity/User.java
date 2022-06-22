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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @Column(name="login")
    private String login;

    @Column(name="password_hash")
    private String passwordHash;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<DiaryPost> diaryPosts = new ArrayList<>();
}
