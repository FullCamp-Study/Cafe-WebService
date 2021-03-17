package com.fullcamp.firebucks.service;

import com.fullcamp.firebucks.domain.Address;
import com.fullcamp.firebucks.domain.Member;
import com.fullcamp.firebucks.domain.Order;
import com.fullcamp.firebucks.domain.enums.OrderStatus;
import com.fullcamp.firebucks.domain.items.Beverage;
import com.fullcamp.firebucks.dto.MemberDTO;
import com.fullcamp.firebucks.exception.NotEnoughStockException;
import com.fullcamp.firebucks.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.aspectj.bridge.MessageUtil.fail;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional

public class OrderServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void 상품주문() throws Exception {

        //given
        Beverage beverage = createBeverage("아메리카노",50,50,10000,10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(createMember(), beverage.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야 한다", 1, getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량이다", 10000 * orderCount, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량만큼 재고가 줄어야 한다", 8, beverage.getStockQuantity());
    }

    @Test
    @Rollback(value = false)
    public void 주문취소() throws Exception {
        //given
        Beverage beverage = createBeverage("아메리카노",50,50,10000,10);

        int orderCount = 2;
        Long orderId = orderService.order(createMember(), beverage.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("주문 취소시 상태는 CANCEL", OrderStatus.CANCEL, getOrder.getStatus());
        Assert.assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다", 10, beverage.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    @Rollback(value = false)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Beverage beverage = createBeverage("아메리카노",50,50,10000,10);

        int orderCount = 11;

        //when
        Long orderId = orderService.order(createMember(), beverage.getId(), orderCount);

        //then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    private Beverage createBeverage(String name, int iced, int caffein, int price, int stockQuantity) {
        Beverage beverage = Beverage.builder()
                .name(name)
                .iced(iced)
                .caffein(caffein)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
        em.persist(beverage);
        return beverage;
    }

    private Long createMember() {
        Address address = Address.builder().city("a").street("b").zipcode("c").build();
        MemberDTO memberDTO = MemberDTO.builder()
                .name("park")
                .password("1234")
                .email("pyh@gmai.com")
                .address(address)
                .build();
        return memberService.join(memberDTO);
    }
}
