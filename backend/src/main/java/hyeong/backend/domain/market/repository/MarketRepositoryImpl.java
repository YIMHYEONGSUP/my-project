package hyeong.backend.domain.market.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.domain.market.dto.MarketItemListResponseDTO;
import hyeong.backend.domain.market.dto.QMarketItemListResponseDTO;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hyeong.backend.domain.item.entity.persist.QItem.*;

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
}
