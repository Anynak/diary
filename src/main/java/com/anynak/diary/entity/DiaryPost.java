package com.anynak.diary.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@SuperBuilder
@Table(name="diary_post")
public class DiaryPost {
    public DiaryPost() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="diary_post_id")
    private Long diaryPostId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="creation_time")
    private Timestamp creationTime;

    @Column(name="text")
    private String text;
}
