package hyeong.backend.domain.member.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import hyeong.backend.domain.member.domain.persist.Member;
import hyeong.backend.domain.member.domain.vo.UserName;
import hyeong.backend.domain.member.domain.vo.UserNickName;
import hyeong.backend.domain.member.domain.vo.UserProfileImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonTypeName("user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class FindAllResponse {

    @JsonProperty("name")
    private UserName name;

    @JsonProperty("nickname")
    private UserNickName nickName;

    @JsonProperty("profile")
    private UserProfileImage image;

    public static FindAllResponse of(final Member member) {
        return new FindAllResponse(member.getName(), member.getNickname(), member.getProfileImage());
    }



}
