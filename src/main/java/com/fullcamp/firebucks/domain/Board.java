package com.fullcamp.firebucks.domain;

import com.fullcamp.firebucks.domain.enums.BoardType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id") //PK 컬럼명이 member_id 이므로 지정
    private Long id;

    private String title;

    private String description;

    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "board") // 멤버는 여러 개의 주문을 가짐.
    private List<Comment> comments = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
