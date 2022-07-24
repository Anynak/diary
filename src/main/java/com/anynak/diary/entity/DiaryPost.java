package com.anynak.diary.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

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


    //@JoinColumn(name = "user_id")
    //@ManyToOne(fetch = FetchType.LAZY)
    //private User user=null;

    @Column(name="creation_time")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;


    @Column(name="text")
    private String text;
}
