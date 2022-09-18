package hyeong.backend.domain.market.service;

import hyeong.backend.domain.market.dto.MarketJoinResponseDTO;
import hyeong.backend.domain.market.dto.MarketResponseDTO;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.global.common.TokenDTO;
import org.springframework.stereotype.Service;

@Service
public interface MarketService {
    MarketJoinResponseDTO create(Market market);

    MarketResponseDTO findByEmail(MarketEmail email);

    TokenDTO update(final Market market, final MarketEmail email);

    void delete(final MarketEmail email);

}
