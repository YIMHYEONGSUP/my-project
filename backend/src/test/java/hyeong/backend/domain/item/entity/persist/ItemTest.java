package hyeong.backend.domain.item.entity.persist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.domain.item.dto.ItemResponseDTO;
import hyeong.backend.domain.item.dto.ItemSearchCondition;
import hyeong.backend.domain.item.dto.QItemResponseDTO;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.item.repository.ItemRepository;
import hyeong.backend.domain.item.service.ItemService;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.persist.Review;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.market.repository.MarketRepository;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.global.TestConfig;
import hyeong.backend.global.common.vo.LocationAddress;
import hyeong.backend.global.common.vo.RoleType;
import hyeong.backend.global.given.GivenItem;
import hyeong.backend.global.given.GivenMarket;
import hyeong.backend.global.given.GivenMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

import java.nio.charset.Charset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@RunWith(SpringRunner.class)
@Import(TestConfig.class)
@Slf4j
class ItemTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    JPAQueryFactory query;

    @Test
    @DisplayName("Repository Test")
    public void repoTest() {

        for (int i = 0; i < 100; i++) {
//            itemRepository.save(GivenItem.createOrderedItem(i));
        }

        QueryResults<Item> itemQueryResults = query.select(QItem.item)
                .from(QItem.item)
                .offset(10)
                .limit(10)
                .fetchResults();

        List<Item> results = itemQueryResults.getResults();
        for (Item result : results) {
            System.out.println("result = " + result.getItemName().name());
        }
    }


    @Test
    @DisplayName("Service Test")
    public void serviceTest() {


    }


}