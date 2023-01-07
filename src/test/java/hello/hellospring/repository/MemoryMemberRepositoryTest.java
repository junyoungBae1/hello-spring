package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach //테스트가 끝날 때마다 데이터를 지워줘야 한다
    public void afterEach(){  //메서드가 끝날 때마다 동작한다.
        repository.clearStore();
    }
    @Test  //테스트할 수 있도록 @Test import해준다.
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring");    //회원 이름을 지정해준다.

        // when
        repository.save(member);      //리포지토리에 회원을 저장해준다.

        // then 검증 : 저장한 것과 꺼낸 것이 동일해야 한다.
        Member result = repository.findById(member.getId()).get();  //저장된 id를 찾아온다, findById의 반환 타입은 Optional. Opntional에서 값을 꺼낼 때 get이용
        //System.out.println("result = " + (result == member));   동일하다면 true로 출력됨
        //Assertions.assertEquals(member, result);  동일하지 않으면 오류 발생 (Assertions : jupiter 사용시)
        assertThat(member).isEqualTo(result);
        
    }
    /*findByName 테스트*/
    @Test
    public void findByName() {

        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName("spring1").get();

        // then
        assertThat(result).isEqualTo(member1);

    }

    /*findAll 테스트*/
    @Test
    public void findAll() {

        //  given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
