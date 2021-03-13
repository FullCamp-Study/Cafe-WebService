package com.fullcamp.firebucks.service;

import com.fullcamp.firebucks.domain.items.Goods;
import com.fullcamp.firebucks.domain.items.Item;
import com.fullcamp.firebucks.dto.ItemDTO;
import com.fullcamp.firebucks.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.awt.print.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 주문_상품_저장() throws Exception {
        //given
        Goods goods = Goods.builder()
                .name("afasdfadsdf")
                .price(10000)
                .company("pakrpark")
                .stockQuantity(20000)
                .build();
        System.out.println("===================================");
        System.out.println(goods);
        itemRepository.save(goods);
        System.out.println(goods);
        Item item2 = itemRepository.findOne(goods.getId());

        System.out.println(item2);
        //when


        //then
    }

    @Test
    @Rollback(value = false)
    public void 주문_상품_수정() throws Exception {
        //given
        ItemDTO itemDTO = ItemDTO.builder()
                .type(ItemDTO.Type.BEVERAGE)
                .name("beverage!!!!1111")
                .price(5500)
                .stockQuantity(30)
                .caffein(10)
                .build();

        Long id = itemService.saveItem(itemDTO);
        Assertions.assertThat(itemDTO.getName()).isEqualTo(itemService.findOne(id).getName());
        System.out.println("12341234");
        ItemDTO itemDTO2 = ItemDTO.builder()
                .id(id)
                .type(ItemDTO.Type.BEVERAGE)
                .name("beverage!!!!1111")
                .price(5400)
                .stockQuantity(30)
                .caffein(3000)
                .build();

        itemService.updateItem(itemDTO2);
        Assertions.assertThat(itemDTO2.getPrice()).isEqualTo(itemService.findOne(id).getPrice());

        // dessert
        ItemDTO dessert = ItemDTO.builder()
                .type(ItemDTO.Type.DESSERT)
                .name("dessert!!!!1111")
                .price(5500)
                .stockQuantity(30)
                .fork_num(3)
                .taste("단맛")
                .build();

        Long dessert_id = itemService.saveItem(dessert);
        Assertions.assertThat(dessert.getName()).isEqualTo(itemService.findOne(dessert_id).getName());
        System.out.println("12341234");

        ItemDTO dessert2 = ItemDTO.builder()
                .id(dessert_id)
                .type(ItemDTO.Type.DESSERT)
                .name("dessert!!!!1111")
                .price(100000)
                .stockQuantity(124340)
                .fork_num(10000)
                .taste("짠   맛")
                .build();


        itemService.updateItem(dessert2);
        Assertions.assertThat(dessert2.getPrice()).isEqualTo(itemService.findOne(dessert_id).getPrice());
    }
}