package hyeong.backend.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import lombok.*;

import javax.validation.Valid;

@Getter
@JsonTypeName("member")
@NoArgsConstructor
public class LoginRequestDTO {

    @Valid
    @JsonProperty("email")
    private MemberEmail email;

    @Valid
    @JsonProperty("password")
    private MemberPassword password;


    @Builder
    public LoginRequestDTO(
            MemberEmail email,
            MemberPassword password
    ) {
        this.email = email;
        this.password = password;
    }
}
