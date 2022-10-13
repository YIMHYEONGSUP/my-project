package hyeong.backend.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import lombok.*;

import javax.validation.Valid;

@Getter
@JsonTypeName("member")
@NoArgsConstructor
public class MemberLoginRequestDTO {

    @Valid
    @JsonProperty("member_email")
    private MemberEmail email;

    @Valid
    @JsonProperty("member_password")
    private MemberPassword password;


    @Builder
    public MemberLoginRequestDTO(
            MemberEmail email,
            MemberPassword password
    ) {
        this.email = email;
        this.password = password;
    }
}
