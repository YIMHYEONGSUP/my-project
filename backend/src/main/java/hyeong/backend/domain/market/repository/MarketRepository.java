package hyeong.backend.domain.market.repository;

import hyeong.backend.domain.market.entity.persist.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {
}
