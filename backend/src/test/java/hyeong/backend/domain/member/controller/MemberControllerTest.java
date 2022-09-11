package hyeong.backend.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import hyeong.backend.domain.member.service.MemberService;
import hyeong.backend.global.configs.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MemberController.class,
            excludeFilters = {
             @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
@AutoConfigureMockMvc(addFilters = false)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private MemberService memberService;


    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setUp(@Autowired WebApplicationContext applicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }



    final Member member = Member.builder()
            .email(MemberEmail.from("1234@gmail.com"))
            .password(MemberPassword.from("1234"))
            .name(MemberName.from("member1"))
            .nickname(MemberNickName.from("nickMember1"))
            .build();

    final MemberJoinRequestDTO request = MemberJoinRequestDTO.builder()
            .email(MemberEmail.from("1234@gmail.com"))
            .password(MemberPassword.from("1234"))
            .name(MemberName.from("member1"))
            .nickname(MemberNickName.from("nickMember1"))
            .build();

    final MemberJoinResponseDTO response = MemberJoinResponseDTO.from(member);
    final MemberResponseDTO dto = MemberResponseDTO.from(member);



    @Test
    @WithMockUser
    public void MemberFindByEmailTest() throws Exception {

        Mockito.when(memberService.findByEmail(request.toEntity().getEmail())).thenReturn(dto);

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("email", "1234@gmail.com");
        info.add("password", "1234");
        info.add("name", "member1");
        info.add("nickname","nickmember1");

        // join parameter 는 email 이아니라 request member 객체이다...
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/join")
                .with(csrf())
                .params(info))
                .andExpect(status().isOk());

        //"email", request.toEntity().getEmail().memberEmail()).contentType(MediaType.APPLICATION_JSON)
    }
}