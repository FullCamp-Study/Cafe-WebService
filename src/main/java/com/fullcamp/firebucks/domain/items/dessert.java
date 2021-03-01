package com.fullcamp.firebucks.domain.items;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dessert")
@Getter
@Setter
public class dessert extends Item{

    private String taste;
    private int fork_num;
}
