package hyeong.backend.domain.market.service;

import hyeong.backend.domain.item.dto.ItemResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.entity.vo.ItemStatus;
import hyeong.backend.domain.item.repository.ItemRepository;
import hyeong.backend.domain.market.controller.MarketControlResponseDTO;
import hyeong.backend.domain.market.dto.LocationCondition;
import hyeong.backend.domain.market.dto.MarketListResponseDTO;
import hyeong.backend.domain.market.dto.item.MarketItemListResponseDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketJoinResponseDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketResponseDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketResponseDTOV2;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static hyeong.backend.domain.market.entity.persist.QMarket.market;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;
    private final ItemRepository itemRepository;

    private final PasswordEncoder encoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder managerBuilder;

    @Override
    public MarketJoinResponseDTO create(Market market) {

        log.info("location info = {}" , market.getMarketLocationAddress().city());

        market.encode(encoder);

        log.info("market role type = {}" , market.getMarketRoleType());

        if (marketRepository.existsByMarketEmail(market.getMarketEmail())) {
            throw new DuplicateEmailException(ErrorCode.EMAIL_DUPLICATION);
        }

        return MarketJoinResponseDTO.from(marketRepository.save(market));
    }

    @Override
    public MarketResponseDTO findByEmail(MarketEmail email) {
        return MarketResponseDTO.create(marketRepository.findByMarketEmail(email).orElseThrow(() -> {
            throw new MarketNotFoundException(ErrorCode.MARKET_NOT_FOUND);
        }));
    }


    @Override
    public MarketResponseDTOV2 findByEmailV2(MarketEmail email) {
        return MarketResponseDTOV2.create(marketRepository.findByMarketEmail(email).orElseThrow(() -> {
            throw new MarketNotFoundException(ErrorCode.MARKET_NOT_FOUND);
        }));
    }

    @Override
    public TokenDTO update(Market market, MarketEmail email) {
        Market findMarket = marketRepository.findByMarketEmail(email).orElseThrow(() -> {
            throw new MarketNotFoundException(ErrorCode.MARKET_NOT_FOUND);
        });

        Market updatedMarket = findMarket.update(market, encoder);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(updatedMarket.getMarketEmail().email(), market.getMarketPassword().password());

        Authentication authentication = managerBuilder.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createTokenMarket(updatedMarket.getMarketEmail().email(), authentication);

    }

    @Override
    public void delete(MarketEmail email) {
        marketRepository.deleteByMarketEmail(email);
    }

    @Override
    public Page<MarketItemListResponseDTO> marketItemList(MarketEmail marketEmail, Pageable pageable) {
        return marketRepository.marketItemList(marketEmail, pageable);
    }

    @Override
    public Page<MarketListResponseDTO> marketList(LocationCondition locationCondition, Pageable pageable) {
        return marketRepository.marketListInLocation(locationCondition, pageable);
    }

    @Override
    @Transactional
    public MarketControlResponseDTO marketStatus(MarketEmail marketEmail, MarketStatus marketStatus, List<Item> preparedItemList) {

        // find market
        Market findMarket = marketRepository.findByMarketEmail(marketEmail).orElseThrow(()->{
            throw new MarketNotFoundException(ErrorCode.MARKET_NOT_FOUND);
        });

        // change market status
        findMarket.changeStatus(marketStatus);

        //

        return MarketControlResponseDTO.from(findMarket);
    }





}
