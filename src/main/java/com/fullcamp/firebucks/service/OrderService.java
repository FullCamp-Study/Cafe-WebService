package com.fullcamp.firebucks.service;

import com.fullcamp.firebucks.domain.Delivery;
import com.fullcamp.firebucks.domain.Member;
import com.fullcamp.firebucks.domain.Order;
import com.fullcamp.firebucks.domain.OrderItem;
import com.fullcamp.firebucks.domain.items.Item;
import com.fullcamp.firebucks.dto.MemberDTO;
import com.fullcamp.firebucks.dto.OrderDTO;
import com.fullcamp.firebucks.repository.ItemRepository;
import com.fullcamp.firebucks.repository.MemberRepository;
import com.fullcamp.firebucks.repository.OrderRepository;
import com.fullcamp.firebucks.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    private Order dtoToEntity(OrderDTO dto) {
        Order order = Order.builder()
                .delivery(dto.getDelivery())
                .orderDate(dto.getOrderDate())
                .member(dto.getMember())
                .status(dto.getStatus())
                .build();
        return order;
    }

    //주문
    @Transactional
    public Long order(Long memberId,Long ItemId, int count){

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(ItemId);

        // 배송정보 조회
        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .build();

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    //취소
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

    //검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAllByString(orderSearch);
//    }
}
