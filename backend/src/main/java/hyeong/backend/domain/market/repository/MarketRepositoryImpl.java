package hyeong.backend.domain.market.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class MarketRepositoryImpl implements MarketRepositoryCustom{
    private JPAQueryFactory query;
    public MarketRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

}
