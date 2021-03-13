package com.fullcamp.firebucks.dto.item;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public abstract class ItemDTO { // 따로 구현체를 가질 것이므로 추상클래스를 사용.

    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

}

