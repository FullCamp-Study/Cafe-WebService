package com.fullcamp.firebucks.domain;

import com.fullcamp.firebucks.domain.items.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // 하나의 아이템에 여러 개의 주문아이템을 가짐. (나이키 신발 -> 주문한 게 다 다르므로)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 하나의 오더가 여러개의 오더 아이템(하나의 결제에 여러 개의 아이템)을 가짐

    private int OrderPrice; // 주문 당시 가격
    private int count; // 주문 수

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem =
        OrderItem.builder()
            .count(count)
            .item(item)
            .OrderPrice(orderPrice)
                .build();

        item.removeStock(count);
        return orderItem;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    //==비지니스 로직==//
    public void cancel() {
        getItem().addStock(count);
    }

    //==조회 로직==//

    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}