package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    //스프링 컨테이너에 등록해서 사용하기 - 하나만 등록
    private final MemberService memberService;

    @Autowired   //스프링이 스프링 컨테이너에 있는 MemberService를 가져다 연결을 시켜줌(Controller랑 Service를 연결시켜줌, dependency injection)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}