package hyeong.backend.controller;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.Dto.Member.MemberDto;
import hyeong.backend.Entity.Member;
import hyeong.backend.Repository.MemberRepository;
import hyeong.backend.aop.LogTrace.LogTraceAspect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Import(LogTraceAspect.class)
class MainControllerTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);
        Member member3 = new Member("member3", 30);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();
    }

    @Test
    public void findAll() {

        PageRequest page = PageRequest.of(0, 10);
        Pageable pageable = Pageable.unpaged();

        Page<MemberDto> members = memberRepository.searchMemberAll(pageable);
        for (MemberDto member : members) {
            System.out.println("member = " + member.toString());
        }

    }

}