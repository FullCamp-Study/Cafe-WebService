package com.fullcamp.firebucks.repository;

import com.fullcamp.firebucks.domain.Delivery;
import com.fullcamp.firebucks.domain.Member;
import com.fullcamp.firebucks.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DeliveryRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Delivery delivery) {
        em.persist(delivery);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }
}
