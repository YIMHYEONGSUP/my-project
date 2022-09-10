package hyeong.backend.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.Valid;


@JsonTypeName("member")
@NoArgsConstructor
public class MemberJoinRequestDTO {

    @Valid
    @JsonProperty("member_email")
    private MemberEmail email;

    @Valid
    @JsonProperty("member_password")
    private MemberPassword password;


    @Valid
    @JsonProperty("member_name")
    private MemberName name;

    @Valid
    @JsonProperty("member_nickname")
    private MemberNickName nickname;


    @Builder
    private MemberJoinRequestDTO(
            MemberEmail email,
            MemberPassword password,
            MemberName name,
            MemberNickName nickname
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }

    public static MemberJoinRequestDTO from(final Member member) {
        return new MemberJoinRequestDTO(member.getEmail() , member.getPassword(), member.getName() , member.getNickname());
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
    }


}
