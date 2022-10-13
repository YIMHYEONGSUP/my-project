package hyeong.backend.domain.market.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyeong.backend.domain.item.service.ItemService;
import hyeong.backend.domain.market.dto.MarketSearchRequestDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketResponseDTOV2;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import hyeong.backend.global.given.GivenMarket;
import hyeong.backend.domain.market.controller.MarketController;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.global.configs.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(controllers = MarketController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })
@AutoConfigureMockMvc
@Slf4j
class MockMarketControllerTest {


    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private MarketService marketService;

    @MockBean
    private ItemService itemService;



    @Autowired
    private WebApplicationContext wac;

    static Market market = GivenMarket.createMarket();


    @BeforeEach
    public void setUp(@Autowired WebApplicationContext applicationContext) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }




    @Test
    @DisplayName("마켓 생성 테스트")
    @WithMockUser
    public void MemberCreateTest() throws Exception {
/*

        MarketJoinResponseDTO responseDTO = MarketJoinResponseDTO.from(market);


        when(marketService.create(market)).thenReturn(responseDTO);

        // responseEntity 로 반환하였기에 201 created / json -> content 로 전달
        mockMvc.perform(post("/api/v1/markets")
                        .content(body)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());*/

    }


    @Test
    @DisplayName("마켓 업데이트 테스트")
    @WithMockUser
    public void marketUpdateTest() throws Exception {


  /*      String body = mapper.writeValueAsString(upDTO);

        mockMvc.perform(patch("/api/v1/markets")
                .with(csrf())
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());*/
    }

    @Test
    @DisplayName("마켓 아이템 리스트 테스트")
    @WithMockUser
    public void MarketItemList() throws Exception{

        MarketSearchRequestDTO requestDTO = MarketSearchRequestDTO.builder()
                .marketEmail(market.getMarketEmail())
                .marketName(market.getMarketName())
                .build();

        String body = mapper.writeValueAsString(requestDTO);

        mockMvc.perform(get("/market/itemList/{marketName}" , "marketName1")
                        .with(csrf())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andReturn();
    }





}