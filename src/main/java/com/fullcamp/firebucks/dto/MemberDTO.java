package com.fullcamp.firebucks.dto;

import com.fullcamp.firebucks.domain.Address;
import com.fullcamp.firebucks.domain.Board;
import com.fullcamp.firebucks.domain.Comment;
import com.fullcamp.firebucks.domain.Order;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {


    private Long id;

    @Email
    private String email;

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @Size(min = 2, max = 20) // min, max는 정수 범위 제한
    private String name;

    private Address address;
}
