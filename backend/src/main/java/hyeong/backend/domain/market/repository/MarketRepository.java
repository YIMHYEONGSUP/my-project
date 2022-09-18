package hyeong.backend.domain.market.repository;

import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketRepository extends JpaRepository<Market, Long> , MarketRepositoryCustom{

    Optional<Market> findByEmail(final MarketEmail email);

    boolean existsByEmail(final MarketEmail email);

    void deleteByEmail(final MarketEmail email);
}
