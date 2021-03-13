package com.fullcamp.firebucks.domain.items;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type")
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item { // 따로 구현체를 가질 것이므로 추상클래스를 사용.

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    public void update(String name, int price, int Quantity) {
        if (!this.name.equals(name))
            this.name = name;
        if (this.price != price)
            this.price = price;
        if (this.stockQuantity != Quantity)
            this.stockQuantity = Quantity;
    }
}
