package com.fullcamp.firebucks.domain.items;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("goods")
@Getter
@Setter
public class goods extends Item{

    private String company;
}
