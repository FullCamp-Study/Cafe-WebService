package com.fullcamp.firebucks.dto;

import com.fullcamp.firebucks.domain.Address;
import com.fullcamp.firebucks.domain.Board;
import com.fullcamp.firebucks.domain.Comment;
import com.fullcamp.firebucks.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class MemberDTO {

    private Long id;

    private String email;

    private String password;

    private String name;

    private Address address;
}
