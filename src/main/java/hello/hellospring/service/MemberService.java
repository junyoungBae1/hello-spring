package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


        public MemberService(MemberRepository memberRepository){
            this.memberRepository = memberRepository;
        }
    /*
    회원 가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member); //중복회원검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //일단 이름을 찾아본다
                .ifPresent(m -> {  //이미 그 이름이 존재한다면,  ifPresent() : null이 아니라 값이 있으면 다음 동작이 실행된다.
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll(); //findAll의 반환 타임 : List
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
