package hyeong.backend.domain.market.service;

import hyeong.backend.domain.market.dto.MarketItemListResponseDTO;
import hyeong.backend.domain.market.dto.MarketJoinResponseDTO;
import hyeong.backend.domain.market.dto.MarketResponseDTO;
import hyeong.backend.domain.market.dto.MarketResponseDTOV2;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.exceptions.MarketNotFoundException;
import hyeong.backend.domain.market.repository.MarketRepository;
import hyeong.backend.domain.member.exceptions.DuplicateEmailException;
import hyeong.backend.global.common.TokenDTO;
import hyeong.backend.global.common.TokenProvider;
import hyeong.backend.global.errors.exceptions.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;
    private final PasswordEncoder encoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder managerBuilder;

    @Override
    public MarketJoinResponseDTO create(Market market) {

        log.info("location info = {}" , market.getLocationAddress().city());

        market.encode(encoder);

        log.info("market role type = {}" , market.getRoleType());

        if (marketRepository.existsByEmail(market.getEmail())) {
            throw new DuplicateEmailException(ErrorCode.EMAIL_DUPLICATION);
        }

        return MarketJoinResponseDTO.from(marketRepository.save(market));
    }

    @Override
    public MarketResponseDTO findByEmail(MarketEmail email) {
        return MarketResponseDTO.create(marketRepository.findByEmail(email).orElseThrow(() -> {
            throw new MarketNotFoundException(ErrorCode.MARKET_NOT_FOUND);
        }));
    }


    @Override
    public MarketResponseDTOV2 findByEmailV2(MarketEmail email) {
        return MarketResponseDTOV2.create(marketRepository.findByEmail(email).orElseThrow(() -> {
            throw new MarketNotFoundException(ErrorCode.MARKET_NOT_FOUND);
        }));
    }

    @Override
    public TokenDTO update(Market market, MarketEmail email) {
        Market findMarket = marketRepository.findByEmail(email).orElseThrow(() -> {
            throw new MarketNotFoundException(ErrorCode.MARKET_NOT_FOUND);
        });

        Market updatedMarket = findMarket.update(market, encoder);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(updatedMarket.getEmail().email(), market.getPassword().password());

        Authentication authentication = managerBuilder.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createTokenMarket(updatedMarket.getEmail().email(), authentication);

    }

    @Override
    public void delete(MarketEmail email) {
        marketRepository.deleteByEmail(email);
    }

    @Override
    public Page<MarketItemListResponseDTO> marketItemList(MarketEmail marketEmail, Pageable pageable) {
        return marketRepository.marketItemList(marketEmail, pageable);
    }
}
