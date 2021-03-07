package com.fullcamp.firebucks.controller;

import javax.validation.Valid;

import com.fullcamp.firebucks.domain.Member;
import com.fullcamp.firebucks.dto.MemberDTO;
import com.fullcamp.firebucks.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/member")
public class testController {

    @Autowired
    MemberService memberService;

    @GetMapping(value = "/save")
    public void saveMember(@Valid MemberDTO memberDTO) {
        // @Valid가 작동하는지 검사하는 컨트롤러 메서드
    }
}
