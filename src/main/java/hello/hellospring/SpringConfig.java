package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //    Autowired
//    DataSource dataSource;
//    private DataSource dataSource; //스프링 부트에서 제공해준다.
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }


//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // memberService를 스프링 빈으로 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); // 스프링 빈에 등록되어 있는 MemberRepository를 MemberService에 넣어줌
    }

    // memberRepository를 스프링 빈으로 등록
//    @Bean
//    public MemberRepository memberRepository() {
    //return new MemoryMemberRepository();
    //return new JdbcMemberRepository(dataSource);
    //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberReporitory(em);
//    }
}