package com.anynak.diary.entity;

import lombok.Data;

import lombok.experimental.SuperBuilder;


import javax.persistence.*;


@Data
@Entity
@SuperBuilder
@Table(name = "diary_post")
public class DiaryPost {
    public DiaryPost() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_post_id")
    private Long diaryPostId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user = null;

    @Column(name = "creation_UNIX_SEC")
    private Long creation_UNIX_SEC;


    @Column(name = "text")
    private String text;
}
