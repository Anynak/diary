package com.anynak.diary.entity;

import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;


@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "diary_post")
public class DiaryPost {

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

    @Override
    public String toString() {
        return "DiaryPost{" +
                "diaryPostId=" + diaryPostId +
                ", creation_UNIX_SEC=" + creation_UNIX_SEC +
                ", text='" + text + '\'' +
                '}';
    }
}
