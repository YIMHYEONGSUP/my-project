package hyeong.backend.domain.item.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.domain.item.dto.*;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.entity.persist.QItem;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static hyeong.backend.domain.item.entity.persist.QItem.*;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private JPAQueryFactory query;
    public ItemRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public Page<ItemResponseDTO> itemList(ItemSearchCondition condition) {

//       query.select(Projections.bean(ItemSearchRequestDTO.class,
//                item.id,
//                item.market,
//                item.itemCategory,
//                item.item
//
//               ))
//
        return null;
    }

    @Override
    public Page<ItemResponseDTO> itemListSimple(ItemSearchCondition condition, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ItemResponseDTO> itemListComplex(ItemSearchCondition condition, Pageable pageable) {
        return null;
    }

    @Override
    public List<ItemResponseDTO> marketItemList(MarketEmail marketEmail) {
        return query.select(
                        new QItemResponseDTO(
                                item.itemCategory,
                                item.itemCode,
                                item.itemStatus,
                                item.itemName,
                                item.itemPrice,
                                item.itemQuantity
                        )
                ).from(item)
                .where(marketEmailEq(marketEmail.email())).fetchResults().getResults();
    }

    // methods

    private BooleanExpression marketEmailEq(String email) {
        return StringUtils.hasText(email) ? item.market.marketEmail.eq(MarketEmail.from(email)) : null;
    }
}
