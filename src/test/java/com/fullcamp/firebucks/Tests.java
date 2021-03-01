package com.fullcamp.firebucks;
import com.fullcamp.firebucks.domain.Board;
import com.fullcamp.firebucks.domain.Comment;
import com.fullcamp.firebucks.domain.Member;
import com.fullcamp.firebucks.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class Tests {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;
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
}
