package hyeong.backend.domain.event.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom{

    private JPAQueryFactory query;

    public EventRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }
}
