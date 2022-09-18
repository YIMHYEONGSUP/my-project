package hyeong.backend.domain.market.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class MarketRepositoryImpl implements MarketRepositoryCustom{
    private JPAQueryFactory query;
    public MarketRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

}
