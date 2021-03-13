package com.fullcamp.firebucks.domain.items;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("beverage")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Beverage extends Item {

    private int caffein;
    private int iced;


}
