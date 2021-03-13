package com.fullcamp.firebucks.dto.item;

import com.fullcamp.firebucks.domain.items.Item;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class dessertDTO extends Item {

    private String taste;
    private int fork_num;
}
