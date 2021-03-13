package com.fullcamp.firebucks.dto.item;

import com.fullcamp.firebucks.domain.items.Item;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class beverageDTO extends Item {

    private int caffein;
    private int iced;
}
