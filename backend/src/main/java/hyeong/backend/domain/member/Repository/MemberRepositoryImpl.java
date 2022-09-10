package hyeong.backend.domain.member.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private JPAQueryFactory query;
    public MemberRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }
}
