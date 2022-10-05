package hyeong.backend.domain.market.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.domain.market.dto.LocationCondition;
import hyeong.backend.domain.market.dto.MarketListResponseDTO;
import hyeong.backend.domain.market.dto.QMarketListResponseDTO;
import hyeong.backend.domain.market.dto.item.MarketItemListResponseDTO;
import hyeong.backend.domain.market.dto.item.QMarketItemListResponseDTO;
import hyeong.backend.domain.market.entity.persist.QMarket;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static hyeong.backend.domain.item.entity.persist.QItem.*;
import static hyeong.backend.domain.market.entity.persist.QMarket.*;

@Repository
@RequiredArgsConstructor
public class MarketRepositoryImpl implements MarketRepositoryCustom{

    @Autowired
    private JPAQueryFactory query;
    public MarketRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public Page<MarketItemListResponseDTO> marketItemList(MarketEmail marketEmail , Pageable pageable) {

        QueryResults<MarketItemListResponseDTO> items = query.select(new QMarketItemListResponseDTO(
                        item.id,
                        item.itemName,
                        item.itemPrice,
                        item.itemQuantity
                ))
                .from(item)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MarketItemListResponseDTO> results = items.getResults();
        long total = items.getTotal();

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public Page<MarketListResponseDTO> marketListInLocation(LocationCondition locationCondition, Pageable pageable) {

        String city = locationCondition.getCity();
        String town = locationCondition.getTown();

        QueryResults<MarketListResponseDTO> result = query.select(new QMarketListResponseDTO(
                        market.marketName.as("market_name"),
                        market.marketStatus.as("market_status"),
                        market.marketLocationAddress.as("market_location_address")
                ))
                .from(market)
                .where(marketCityEq(locationCondition.getCity()),
                        marketTownEq(locationCondition.getTown()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MarketListResponseDTO> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }


    // where condition check methods

    private BooleanExpression marketCityEq(String city) {
        return StringUtils.hasText(city) ? market.marketLocationAddress.city.eq(city) : null;
    }

    private BooleanExpression marketTownEq(String town) {
        return StringUtils.hasText(town) ? market.marketLocationAddress.town.eq(town) : null;
    }


}
