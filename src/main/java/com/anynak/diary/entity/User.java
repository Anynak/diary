package com.anynak.diary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data
@RequiredArgsConstructor
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name="user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;


    @OneToMany(
            fetch = FetchType.LAZY
            ,cascade = CascadeType.ALL
            //,mappedBy = "user"
    )
    @JoinColumn(name = "user_id", nullable = false)
    private List<DiaryPost> diaryPosts=new ArrayList<>();

    @NonNull
    @Column(name="login", unique = true)
    private String login;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;





    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles=null;

    public void addRole(Role role){
        if(roles==null){
            roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public void addDiaryPosts(DiaryPost diaryPost){
        if(diaryPosts==null){
            diaryPosts = new ArrayList<>();
        }
        this.diaryPosts.add(diaryPost);
    }
    public void removeRole(Role role){
        this.roles.remove(role);
    }
}
