package hyeong.backend.domain.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private JPAQueryFactory query;
    public ItemRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

}
