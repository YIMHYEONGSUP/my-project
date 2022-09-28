package hyeong.backend.domain.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyeong.backend.domain.auth.dto.MarketLoginRequestDTO;
import hyeong.backend.domain.auth.service.MarketAuthService;
import hyeong.backend.global.given.GivenMarket;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketPassword;
import hyeong.backend.global.configs.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = MarketAuthController.class ,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE , classes = SecurityConfig.class))
@Slf4j
class MarketAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @MockBean
    MarketAuthService marketAuthService;

    ObjectMapper mapper = new ObjectMapper();

    static Market market = GivenMarket.createMarket();

    @BeforeEach
    public void setup(@Autowired WebApplicationContext context) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .apply(springSecurity())
                .alwaysDo(print())
                .build();

    }

    @Test
    @DisplayName("마켓 회원 로그인")
//    @WithMockCustomerMarket
    public void marketLoginTest() throws Exception {

        MarketLoginRequestDTO requestDTO = MarketLoginRequestDTO.builder()
                .email(MarketEmail.from("market@Naver.com"))
                .password(MarketPassword.from("1234"))
                .build();

        String body = mapper.writeValueAsString(requestDTO);

        // MockMvcRequestBuilders -> add on demand static import
        mockMvc.perform(post("/api/v1/markets/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
    }


}