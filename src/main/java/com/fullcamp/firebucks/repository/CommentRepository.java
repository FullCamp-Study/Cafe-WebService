package com.fullcamp.firebucks.repository;

import com.fullcamp.firebucks.domain.Board;
import com.fullcamp.firebucks.domain.Comment;
import com.fullcamp.firebucks.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment){
        em.persist(comment);
    }

    public List<Comment> findByBoard(Board board){
        return em.createQuery("select c from Comment c where c.board = :board",
                Comment.class)
                .setParameter("board", board)
                .getResultList();
    }
}
