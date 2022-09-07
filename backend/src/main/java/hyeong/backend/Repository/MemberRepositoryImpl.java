package hyeong.backend.Repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.Dto.Member.MemberDto;
import hyeong.backend.Dto.Member.MemberSearchCondition;
import hyeong.backend.Entity.QMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static hyeong.backend.Entity.QMember.*;


@Slf4j
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<MemberDto> searchMemberAll(Pageable pageable) {
        
        log.info("안됨안됨");
        
        QueryResults<MemberDto> results = queryFactory
                .select(
                        Projections.fields(MemberDto.class,
                                member.id.as("id"),
                                member.username.as("username"),
                                member.age.as("age")
                                ))
                .from(member)
                .fetchResults();

        log.info("result는 만들어짐");

        List<MemberDto> content = results.getResults();
        long total = results.getTotal();

        log.info("다 잘됨 만들어짐");
        
        return new PageImpl<>(content, pageable, total);
    }
}
