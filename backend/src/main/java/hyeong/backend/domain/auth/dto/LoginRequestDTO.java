package hyeong.backend.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import lombok.*;

@Getter
@JsonTypeName("member")
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT , use = JsonTypeInfo.Id.NAME)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class LoginRequestDTO {

    @JsonProperty("email")
    private MemberEmail email;

    @JsonProperty("password")
    private MemberPassword password;

}
