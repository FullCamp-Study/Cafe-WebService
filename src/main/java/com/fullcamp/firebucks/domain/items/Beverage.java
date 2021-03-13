package com.fullcamp.firebucks.domain.items;

import com.fullcamp.firebucks.dto.ItemDTO;
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

    @Override
    public void update(ItemDTO itemDTO) {
        super.update(itemDTO);
        this.caffein = itemDTO.getCaffein();
        this.iced = itemDTO.getIced();
    }

}
