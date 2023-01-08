package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {     //회원 저장
        member.setId(++sequence);           //시스템 id 지정해주기
        store.put(member.getId(), member);  //저장소에 넣어주기
        return member;                      //저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {     //회원 찾기
        return Optional.ofNullable(store.get(id));  //null이 반환될 가능성이 있으므로 Optional.ofNullable로 감싸주어야 한다.
    }

    @Override
    public Optional<Member> findByName(String name) { //회원 찾기
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    //회원의 name과 파라미터로 들어온 name과 일치하는 지 확인.
                .findAny();      //하나라도 찾는 것. 결과가 null이면 Optional로 감싸져서 반환된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  //회원 목록 반환.
    }

    public void clearStore() {
        store.clear();
    }
}
