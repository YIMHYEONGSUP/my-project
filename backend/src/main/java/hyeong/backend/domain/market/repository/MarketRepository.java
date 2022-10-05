package hyeong.backend.domain.market.repository;

import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketRepository extends JpaRepository<Market, Long> , MarketRepositoryCustom{

    Optional<Market> findByMarketEmail(final MarketEmail email);

    boolean existsByMarketEmail(final MarketEmail email);

    void deleteByMarketEmail(final MarketEmail email);
}
