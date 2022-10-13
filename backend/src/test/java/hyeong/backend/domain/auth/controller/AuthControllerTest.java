package hyeong.backend.domain.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyeong.backend.domain.auth.config.WithMockCustomMember;
import hyeong.backend.domain.auth.details.CustomUserDetailsService;
import hyeong.backend.domain.auth.dto.MemberLoginRequestDTO;
import hyeong.backend.domain.auth.service.MemberAuthService;
import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.controller.MemberController;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.global.given.GivenMember;
import hyeong.backend.domain.member.service.MemberService;
import hyeong.backend.global.common.TokenDTO;
import hyeong.backend.global.common.TokenProvider;
import hyeong.backend.global.jwt.JwtAccessDeniedHandler;
import hyeong.backend.global.jwt.JwtAuthenticationEntryPoint;
import hyeong.backend.global.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;

import java.io.DataInput;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class
//       , excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
//       }
)
@Slf4j
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    WebApplicationContext context;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtAccessDeniedHandler accessDeniedHandler;

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private MemberService memberService;

    @MockBean
    private CustomUserDetailsService customMemberDetailService;

    @MockBean
    private RedisService redisService;

    @MockBean
    private CorsFilter corsFilter;

    @MockBean
    private MemberAuthService memberAuthService;

    @MockBean
    private MemberRepository memberRepository;


    ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    @Profile("dev")
    public void settingUserTest() {
        Member member = GivenMember.createMember();
        memberRepository.save(member);
    }

    @BeforeEach
    public void setup(@Autowired WebApplicationContext context) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(springSecurity())
                .alwaysDo(print())
                .build();

    }


    static Member member = GivenMember.createMember();

     MemberJoinRequestDTO memberJoinRequestDTO = MemberJoinRequestDTO.from(member);
     MemberJoinResponseDTO memberJoinResponseDTO = MemberJoinResponseDTO.from(member);
     MemberResponseDTO memberResponseDTO = MemberResponseDTO.create(member);

    @Test
    @DisplayName("멤버 로그인 테스트")
//    @WithMockUser(username = "gud1313@naver.com",password = "1234",roles = "USER")
    @WithMockCustomMember   // default 값이 존재하기 때문에 위와 같은 초기화 X , authentication 값도 있음
    public void loginTest() throws Exception {

        MemberLoginRequestDTO loginRequestDTO = MemberLoginRequestDTO.builder()
                .email(member.getMemberEmail())
                .password(member.getMemberPassword())
                .build();

        String body = mapper.writeValueAsString(loginRequestDTO);

        TokenDTO tokenDTO = memberAuthService.login(member.getMemberEmail(), member.getMemberPassword());

//        when(memberAuthService.authorize(any(), any())).thenReturn(tokenDTO);
        when(memberAuthService.login(any(), any())).thenReturn(tokenDTO);
        log.info("tokenDTO = {}" , tokenDTO);

        MockHttpServletResponse content = mockMvc.perform(post("/api/v1/members/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(body)
                        .header("Authentication", SecurityContextHolder.getContext().getAuthentication()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        MockHttpServletResponse print = mapper.readValue((DataInput) content, MockHttpServletResponse.class);
        log.info("print = {}" , print);

    }

    @Test
    @DisplayName("jwt 테스트")
    @WithMockCustomMember
    public void jwtTest() throws Exception {

        mockMvc.perform(post("/api/v1/members/login")
                        .with(csrf().asHeader()))
                .andDo(print());
    }

    @Test
    @DisplayName("jwt 테스트")
    @WithMockCustomMember
    public void jwtTest2() throws Exception {

        MemberJoinResponseDTO mock = when(memberService.create(member)).getMock();

        System.out.println("mock = " + mock.getEmail().memberEmail());

    }

    @Value("${jwt.refreshToken-validity-in-seconds}")
    private long refresh_second;

    @Test
    @DisplayName("Redis 테스트")
    @WithMockCustomMember
    public void redisJwt() throws Exception {


    }


}