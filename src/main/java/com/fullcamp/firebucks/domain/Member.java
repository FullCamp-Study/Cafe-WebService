package com.fullcamp.firebucks.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") //PK 컬럼명이 member_id 이므로 지정
    private Long id;

    private String email;

    private String password;

    private String name;

    @OneToMany(mappedBy = "member") // 멤버는 여러 개의 주문을 가짐.
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member") // 멤버는 여러 개의 주문을 가짐.
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Embedded // 내장 타입
    private Address address;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
