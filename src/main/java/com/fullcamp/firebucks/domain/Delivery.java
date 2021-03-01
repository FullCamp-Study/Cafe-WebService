package com.fullcamp.firebucks.domain;


import com.fullcamp.firebucks.domain.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    // 하나의 배송은 하나의 주정보만을 가진다.
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;


//  @Enumerated(EnumType.STRING)은 컬럼에 String값으로 들어가고
//  @Enumerated(EnumType.ORDINARY)는 컬럼에 숫자 순서로 들어가므로
//  나중에 수정에 대응하기 위해서 항상 STRING으로 써줘야 한다.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus; // READY, COMP
}
