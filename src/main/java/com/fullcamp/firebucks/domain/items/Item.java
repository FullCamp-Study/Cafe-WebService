package com.fullcamp.firebucks.domain.items;

import com.fullcamp.firebucks.dto.ItemDTO;
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

    public void update(ItemDTO itemDTO) {
        if (!this.name.equals(itemDTO.getName()))
            this.name = itemDTO.getName();
        if (this.price != itemDTO.getPrice())
            this.price = itemDTO.getPrice();
        if (this.stockQuantity != itemDTO.getStockQuantity())
            this.stockQuantity = itemDTO.getStockQuantity();
    }
}
