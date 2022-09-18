package hyeong.backend.domain.market.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private JPAQueryFactory query;

    public ReviewRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }
}
