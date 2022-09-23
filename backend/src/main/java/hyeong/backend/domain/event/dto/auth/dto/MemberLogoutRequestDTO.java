package hyeong.backend.domain.event.dto.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.global.common.AccessToken;
import hyeong.backend.global.common.RefreshToken;
import lombok.Getter;

@Getter
@JsonTypeName("member")
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class MemberLogoutRequestDTO {

    @JsonProperty("accessToken")
    private AccessToken accessToken;

    @JsonProperty("refreshToken")
    private RefreshToken refreshToken;
}
