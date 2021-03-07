package com.fullcamp.firebucks.service;

import com.fullcamp.firebucks.domain.Address;
import com.fullcamp.firebucks.dto.MemberDTO;
import com.fullcamp.firebucks.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Address address = Address.builder().city("a").street("b").zipcode("c").build();
        MemberDTO memberDTO = MemberDTO.builder()
                .name("park")
                .password("1234")
                .email("pyh@gmai.com")
                .address(address)
                .build();
        //when
        Long idx = memberService.join(memberDTO);
        //then
        System.out.println(memberDTO);
        System.out.println(memberRepository.findOne(1L));
        Assertions.assertThat(idx).isEqualTo(1L);
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원검사() throws Exception {
        //given
        Address address1 = Address.builder().city("a").street("b").zipcode("c").build();
        MemberDTO memberDTO1 = MemberDTO.builder()
                .name("park")
                .password("445511421234")
                .email("pyh@gmai.com")
                .address(address1)
                .build();

        Address address2 = Address.builder().city("a").street("b").zipcode("d").build();
        MemberDTO memberDTO2 = MemberDTO.builder()
                .name("park")
                .password("123441241")
                .email("14241p1yh@gmai.com")
                .address(address2)
                .build();

        //when 예외가 터지면 성공
        Long idx1 = memberService.join(memberDTO1);
        Long idx2 = memberService.join(memberDTO2);

        //then 안터지면 실패
        fail("중복 회원 검사 실패!!");
    }
}