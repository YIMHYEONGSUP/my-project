package hyeong.backend.domain.item.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.service.MarketService;
import hyeong.backend.global.given.GivenItem;
import hyeong.backend.domain.item.dto.ItemRegisterRequestDTO;
import hyeong.backend.domain.item.dto.ItemRegisterResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.service.ItemService;
import hyeong.backend.global.configs.SecurityConfig;
import hyeong.backend.global.given.GivenMarket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = ItemController.class
        , excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
@AutoConfigureWebMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ItemService itemService;

    @MockBean
    private MarketService marketService;

    ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setUp(@Autowired WebApplicationContext applicationContext) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    static Item item = GivenItem.createItem();
    static Market market = GivenMarket.createMarket();

    @Test
    @DisplayName("아이템 생성 테스트")
    @WithMockUser
    public void MemberCreateTest() throws Exception {

        item.setMarket(market);

        ItemRegisterRequestDTO request = ItemRegisterRequestDTO.builder()
                .itemCategory(ItemCategory.FOOD)
                .itemCode(ItemCode.KOREAN)
                .itemStatus(ItemStatus.FOR_SALE)
                .itemName(ItemName.from("고추장 불고기"))
                .itemPrice(ItemPrice.from(13000L))
                .itemQuantity(ItemQuantity.from(100L))
                .build();

        ItemRegisterResponseDTO response = ItemRegisterResponseDTO.from(item);

        String body = mapper.writeValueAsString(request);

        mockMvc.perform(post("/item")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn()
                .getResponse();


    }


}