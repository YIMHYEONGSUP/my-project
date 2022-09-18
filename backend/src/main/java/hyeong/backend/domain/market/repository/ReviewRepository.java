package hyeong.backend.domain.market.repository;

import hyeong.backend.domain.market.entity.persist.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review , Long> , ReviewRepositoryCustom{
}
