package hyeong.backend.domain.event.repository;

import hyeong.backend.domain.event.entity.persist.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event , Long> {
}
