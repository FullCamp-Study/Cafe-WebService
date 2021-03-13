package com.fullcamp.firebucks.repository;

import com.fullcamp.firebucks.domain.items.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void deleteById(Long id) {
        em.remove(findOne(id));
    }

    public void save(Item item) {
        System.out.println("AAAA" + item.getId());
        if (item.getId() == null) {
            em.persist(item);
        }
        else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

    public List<Item> findByName(String name) {
        return em.createQuery("select i from Item i where i.name = :name", Item.class).
                setParameter("name", name).getResultList();
    }
}
