package hyeong.backend.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embedded;

@Getter
@NoArgsConstructor
@JsonTypeName("market")
public class MemberResponseDTO {

    @JsonProperty("email")
    private MemberEmail email;

    @JsonProperty("name")
    private MemberName name;

    @JsonProperty("nickname")
    private MemberNickName nickname;

    @Builder
    public MemberResponseDTO(
            MemberEmail email,
            MemberName name,
            MemberNickName nickname
    ) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }


    public static MemberResponseDTO create(final Member member) {
        return new MemberResponseDTO(member.getEmail(), member.getName(),
                member.getNickname());
    }

}
