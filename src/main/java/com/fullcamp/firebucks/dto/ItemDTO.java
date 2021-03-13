package com.fullcamp.firebucks.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO { // 따로 구현체를 가질 것이므로 추상클래스를 사용.

    public enum Type {
        BEVERAGE, GOODS, DESSERT
    };

    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    // goods
    private String company;

    //dessert
    private String taste;
    private int fork_num;

    // beverage
    private int caffein;
    private int iced;

    private Type type;
}

