package hyeong.backend.domain.member.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private JPAQueryFactory query;
    public MemberRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }
}
