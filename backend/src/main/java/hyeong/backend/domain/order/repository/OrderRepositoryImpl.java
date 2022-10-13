package hyeong.backend.domain.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrdersRepositoryCustom {
    private JPAQueryFactory query;
    public OrderRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }
}
