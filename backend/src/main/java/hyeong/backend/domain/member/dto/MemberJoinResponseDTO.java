package hyeong.backend.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonTypeName("member")
@AllArgsConstructor
public class MemberJoinResponseDTO {

    @JsonProperty("email")
    private MemberEmail email;

    @JsonProperty("name")
    private MemberName name;

    @JsonProperty("nickname")
    private MemberNickName nickName;

    public static MemberJoinResponseDTO from(final Member member) {
        return new MemberJoinResponseDTO(member.getEmail(), member.getName(), member.getNickname());
    }

}
