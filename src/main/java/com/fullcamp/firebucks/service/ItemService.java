package com.fullcamp.firebucks.service;

import com.fullcamp.firebucks.domain.items.Dessert;
import com.fullcamp.firebucks.domain.items.Goods;
import com.fullcamp.firebucks.domain.items.Item;
import com.fullcamp.firebucks.domain.items.Beverage;
import com.fullcamp.firebucks.dto.ItemDTO;
import com.fullcamp.firebucks.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 상품 등
     * @param itemDTO
     */
    @Transactional
    public Long saveItem(ItemDTO itemDTO) {
        validateDuplicateMember(itemDTO.getName());

        Item item = dtoToEntity(itemDTO);
        System.out.println("------------------------");
        System.out.println(item);
        itemRepository.save(item);
        return item.getId();
    }

    // 변경 감지를 통한 변

    /**
     *
     * @param itemDTO
     */
    @Transactional
    public void updateItem(ItemDTO itemDTO) {
        Item findItem = itemRepository.findOne(itemDTO.getId());
        findItem.update(itemDTO);
        // 트랜잭션 안에서 다시 조회한 객체를 변경 하면 커밋 시점에 더티 체킹에 의해 DB에 UPDATE 쿼리가 날라간다.
    }

    @Transactional
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public void validateDuplicateMember(String name) {
        // 중복 상 EXCEPTION 처리
        List<Item> findItems = itemRepository.findByName(name);
        if (!findItems.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 상입니다.");
        }
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    private Item dtoToEntity(ItemDTO dto) {

        Item item = null;

        if (dto.getType() == ItemDTO.Type.BEVERAGE) {
            item = Beverage.builder()
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .stockQuantity(dto.getStockQuantity())
                    .caffein(dto.getCaffein())
                    .iced(dto.getIced())
                    .build();
        }
        else if (dto.getType() == ItemDTO.Type.DESSERT) {
            item = Dessert.builder()
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .stockQuantity(dto.getStockQuantity())
                    .taste(dto.getTaste())
                    .fork_num(dto.getFork_num())
                    .build();
        }
        else if (dto.getType() == ItemDTO.Type.GOODS) {
            item = Goods.builder()
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .stockQuantity(dto.getStockQuantity())
                    .company(dto.getCompany())
                    .build();
        }
        return item;
    }
}
