package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //첫 줄에서의 MemberService 내에 있는 memberRepository와 두번째 줄에서의 memberRepository는 서로 다른 객체이다.
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //MemoryMemberRepository를 만들어 넣어줌으로써 같은 메모리 리포지토리를 사용하게 됨
    }
    @AfterEach //테스트가 끝날 때마다 데이터를 지워줘야 한다
    public void afterEach(){  //메서드가 끝날 때마다 동작한다.
        memberRepository.clearStore();
    }
    @Test
    public void 회원가입() {

        //given 무엇인가 주어졌을 때
        Member member = new Member();
        member.setName("spring");

        //when 이거를 실행을 시키고
        Long saveId = memberService.join(member);

        //then 결과가 이게 나와야 한다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test
    public void 중복_회원_예외() {

        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
//        memberService.join(member1);
//        try {       //실행할 코드
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {     //예외
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//assertThrows : 예외 처리 메서드, ()->가 실행되고 예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}