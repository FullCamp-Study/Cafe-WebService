package com.fullcamp.firebucks;
import com.fullcamp.firebucks.domain.Board;
import com.fullcamp.firebucks.domain.Comment;
import com.fullcamp.firebucks.domain.Member;
import com.fullcamp.firebucks.repository.BoardRepository;
import com.fullcamp.firebucks.repository.CommentRepository;
import com.fullcamp.firebucks.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class Tests {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void test() throws Exception {

        //given
        Member member = new Member();
        member.setName("park");

        memberRepository.save(member);

//        Board board = Board.builder().member(member).

//        Comment comment = Comment.builder().member(member).description("goooooood 박윤환").

        //when

        //then
    }

    @Test
    public void 게시판() throws Exception{
        Member member = new Member();
        member.setName("park");

        memberRepository.save(member);

        Board board1 = new Board();
        board1.setTitle("하이");
        board1.setDescription("하이하이");
        board1.setMember(member);

        Board board2 = new Board();
        board2.setTitle("바이");
        board2.setDescription("바이바이");
        board2.setMember(member);

        em.persist(member);
        em.persist(board1);
        em.persist(board2);

        List<Board> boardListByMember = boardRepository.findByMember(member);

        Assertions.assertThat(boardListByMember.get(0)).isEqualTo(board1);
        Assertions.assertThat(boardListByMember.get(1)).isEqualTo(board2);

        System.out.println("boardListMember = "+boardListByMember);
    }

    @Test
    public void 댓글() throws Exception{
        Member member = new Member();
        member.setName("park");

        memberRepository.save(member);

        Board board1 = new Board();
        board1.setTitle("하이");
        board1.setDescription("하이하이");
        board1.setMember(member);

        em.persist(member);
        em.persist(board1);

        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        comment1.setDescription("댓글1");
        comment2.setDescription("댓글2");
        comment1.setBoard(board1);
        comment2.setBoard(board1);

        em.persist(comment1);
        em.persist(comment2);

        List<Comment> commentListByBoard = commentRepository.findByBoard(board1);

        System.out.println("size"+commentListByBoard.size());

        Assertions.assertThat(commentListByBoard.get(0)).isEqualTo(comment1);
        Assertions.assertThat(commentListByBoard.get(1)).isEqualTo(comment2);

        System.out.println("boardListMember = "+commentListByBoard);
    }
}
