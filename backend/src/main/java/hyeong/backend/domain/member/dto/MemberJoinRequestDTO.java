package hyeong.backend.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;


@Getter
@JsonTypeName("member")
@NoArgsConstructor
public class MemberJoinRequestDTO {

    @Valid
    @JsonProperty("email")
    private MemberEmail email;

    @Valid
    @JsonProperty("password")
    private MemberPassword password;


    @Valid
    @JsonProperty("name")
    private MemberName name;

    @Valid
    @JsonProperty("nickname")
    private MemberNickName nickname;


    @Builder
    public MemberJoinRequestDTO(
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
                .roleType(RoleType.USER)
                .build();
    }


}
