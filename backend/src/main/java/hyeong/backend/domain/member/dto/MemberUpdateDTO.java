package hyeong.backend.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@ApiModel
@JsonTypeName("member")
@Getter
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class MemberUpdateDTO {

    @JsonProperty("email")
    @ApiModelProperty(example = "golf@gmail.com")
    private MemberEmail email;

    @JsonProperty("password")
    @ApiModelProperty(example = "3245")
    private MemberPassword password;

    @JsonProperty("nickname")
    @ApiModelProperty(example = "golf")
    private MemberNickName nickName;

    private MemberUpdateDTO(MemberEmail email, MemberPassword password, MemberNickName nickName){
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public static MemberUpdateDTO from(final Member member) {
        return new MemberUpdateDTO(member.getEmail(), member.getPassword(), member.getNickname());
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickName)
                .build();
    }
}
