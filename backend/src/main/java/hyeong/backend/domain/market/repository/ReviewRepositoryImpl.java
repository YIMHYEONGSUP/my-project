package hyeong.backend.domain.market.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private JPAQueryFactory query;
    public ReviewRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }
}
