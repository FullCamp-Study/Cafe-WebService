package com.fullcamp.firebucks.dto;

import com.fullcamp.firebucks.domain.Delivery;
import com.fullcamp.firebucks.domain.Member;
import com.fullcamp.firebucks.domain.OrderItem;
import com.fullcamp.firebucks.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private Member member;

    private Delivery delivery;

    private LocalDateTime orderDate;

    private OrderStatus status;
}

