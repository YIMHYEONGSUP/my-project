package hyeong.backend.domain.member.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.member.domain.persist.Member;
import hyeong.backend.domain.member.domain.vo.UserEmail;
import hyeong.backend.domain.member.domain.vo.UserNickName;
import hyeong.backend.domain.member.domain.vo.UserPassword;
import hyeong.backend.domain.member.domain.vo.UserProfileImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@ApiModel
@JsonTypeName("user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateDTO {

    @JsonProperty("email")
    @ApiModelProperty(example = "1234@gmail.com")
    private UserEmail email;

    @JsonProperty("password")
    @ApiModelProperty(example = "1234")
    private UserPassword password;

    @JsonProperty("nickname")
    @ApiModelProperty(example = "(구)양배추")
    private UserNickName nickName;

    @JsonProperty("profile")
    @ApiModelProperty(example = "/user/image/new_image.jpeg")
    private UserProfileImage profileImage;

    private MemberUpdateDTO(UserEmail email, UserPassword password, UserNickName nickName,
                            UserProfileImage profileImage) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.profileImage = profileImage;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickName)
                .profileImage(profileImage)
                .build();
    }

}
