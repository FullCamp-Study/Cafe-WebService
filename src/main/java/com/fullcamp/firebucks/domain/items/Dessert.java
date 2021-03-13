package com.fullcamp.firebucks.domain.items;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dessert")
@Getter
@NoArgsConstructor
@SuperBuilder
public class Dessert extends Item{

    private String taste;
    private int fork_num;
}
