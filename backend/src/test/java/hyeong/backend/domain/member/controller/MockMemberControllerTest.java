package hyeong.backend.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.dto.MemberUpdateDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.util.GivenMember;
import hyeong.backend.domain.member.entity.vo.*;
import hyeong.backend.domain.member.service.MemberService;
import hyeong.backend.global.configs.SecurityConfig;
import hyeong.backend.global.configs.WebMvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import javax.persistence.Embedded;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MemberController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    })
@AutoConfigureMockMvc
class MockMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private MemberService memberService;


    @Autowired
    private WebApplicationContext wac;



    @BeforeEach
    public void setUp(@Autowired WebApplicationContext applicationContext) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }


    static Member member = GivenMember.createMember();


    @Test
    @DisplayName("멤버 생성 테스트")
    @WithMockUser
    public void MemberCreateTest() throws Exception {

        MemberJoinRequestDTO requestMember = MemberJoinRequestDTO.from(member);
        MemberJoinResponseDTO responseMember = MemberJoinResponseDTO.from(member);

        String body = mapper.writeValueAsString(requestMember);

        when(memberService.create(any())).thenReturn(responseMember);

        // responseEntity 로 반환하였기에 201 created / json -> content 로 전달
        mockMvc.perform(post("/api/v1/members")
                .content(body)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }


    @Test
    @DisplayName("회원 수정 인수 테스트")
    @WithMockUser(username = "gud1313@naver.com", roles = "USER")
    void updateMember() throws Exception {
        String body = mapper.writeValueAsString(MemberUpdateDTO.from(member));

        mockMvc.perform(patch("/api/v1/members").content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("회원 삭제 인수 테스트")
    @WithMockUser(username = "ssar@naver.com", roles = "USER")
    void deleteMember() throws Exception {
        mockMvc.perform(delete("/api/v1/members"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 조회 인수 테스트")
    @WithMockUser(username = "ssar@naver.com", roles = "USER")
    void findMember() throws Exception {
        MemberResponseDTO memberResponseDTO = MemberResponseDTO.create(member);

        when(memberService.findByEmail(any())).thenReturn(memberResponseDTO);

        mockMvc.perform(get("/api/v1/members/findByEmail")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


}