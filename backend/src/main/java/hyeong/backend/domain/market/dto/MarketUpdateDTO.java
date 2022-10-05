package hyeong.backend.domain.market.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.member.dto.MemberUpdateDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import hyeong.backend.global.common.vo.LocationAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@ApiModel
@JsonTypeName("market")
@Getter
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class MarketUpdateDTO {

    @JsonProperty("market_email")
    @ApiModelProperty(example = "gud1313@gmail.com")
    private MarketEmail email;

    @JsonProperty("market_password")
    @ApiModelProperty(example = "1234")
    private MarketPassword password;

    @JsonProperty("market_name")
    @ApiModelProperty(example = "Hyeong")
    private MarketName name;

    @JsonProperty("market_locationAddress")
    @ApiModelProperty(example = "서울 , 강남 , ㅇㅇ빌딩 , 12345")
    private LocationAddress locationAddress;


    @JsonCreator
    public MarketUpdateDTO (
            @JsonProperty("market_email") final MarketEmail email, @JsonProperty("market_password") final MarketPassword password,
            @JsonProperty("market_name") final MarketName name,  @JsonProperty("market_location_address") LocationAddress locationAddress
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.locationAddress = locationAddress;
    }

    public Market toEntity() {
        return Market.builder()
                .email(email)
                .password(password)
                .name(name)
                .locationAddress(locationAddress)
                .build();
    }
}
