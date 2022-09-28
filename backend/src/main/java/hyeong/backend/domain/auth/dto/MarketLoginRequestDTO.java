package hyeong.backend.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketPassword;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@JsonTypeName("market")
@NoArgsConstructor
public class MarketLoginRequestDTO {

    @Valid
    @JsonProperty("email")
    private MarketEmail email;

    @Valid
    @JsonProperty("password")
    private MarketPassword password;


    @Builder
    public MarketLoginRequestDTO(
            MarketEmail email,
            MarketPassword password
    ) {
        this.email = email;
        this.password = password;
    }
}
