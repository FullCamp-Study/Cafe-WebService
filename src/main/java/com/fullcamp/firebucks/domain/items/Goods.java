package com.fullcamp.firebucks.domain.items;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("goods")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Goods extends Item{

    private String company;

    @Override
    public void update(String name, int price, int Quantity, String company) {
        super.update(name, price, Quantity);
        this.company = company;
    }
}
