package com.fullcamp.firebucks.domain.items;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("beverage")
@Getter
@Setter
public class beverage extends Item{

    private int caffein;
    private int iced;
}
