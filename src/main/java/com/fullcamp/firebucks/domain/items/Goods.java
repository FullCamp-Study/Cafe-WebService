package com.fullcamp.firebucks.domain.items;

import com.fullcamp.firebucks.dto.ItemDTO;
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
    public void update(ItemDTO itemDTO) {
        super.update(itemDTO);
        this.company = itemDTO.getCompany();
    }
}
