package hyeong.backend.domain.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyeong.backend.domain.auth.dto.LoginRequestDTO;
import hyeong.backend.domain.auth.service.MemberAuthService;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import hyeong.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberAuthService memberAuthService;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup(@Autowired WebApplicationContext context) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
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
    final MemberResponseDTO memberResponseDTO = MemberResponseDTO.create(member);


    @Test
    @WithMockUser
    public void loginTest() throws Exception {

        Mockito.when(memberService.create(member)).thenReturn(response);

        Mockito.when(memberAuthService.authorize(member.getEmail() , member.getPassword()))
                        .toString();


        mockMvc.perform(post("/api/v1/members/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"1234@gmail\",\"password\":\"1234\"}"))
                .andDo(print())
                .andExpect(status().isCreated());

    }


}