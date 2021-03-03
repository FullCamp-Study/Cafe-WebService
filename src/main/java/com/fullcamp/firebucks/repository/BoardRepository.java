package com.fullcamp.firebucks.repository;

import com.fullcamp.firebucks.domain.Board;
import com.fullcamp.firebucks.domain.Member;
import com.fullcamp.firebucks.domain.items.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board){
        em.persist(board);
    }

    public Board findOne(Long id){
        return em.find(Board.class,id);
    }

    public List<Board> findByMember(Member member){
        return em.createQuery("select b from Board b where b.member = :member",
                Board.class)
                .setParameter("member", member)
                .getResultList();
    }
}
