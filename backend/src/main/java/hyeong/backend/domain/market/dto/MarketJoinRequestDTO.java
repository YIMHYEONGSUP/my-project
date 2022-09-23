package hyeong.backend.domain.market.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.global.common.vo.LocationAddress;
import hyeong.backend.global.common.vo.RoleType;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@JsonTypeName("market")
public class MarketJoinRequestDTO{

    @JsonProperty("name")
    private MarketName name;

    @JsonProperty("email")
    private MarketEmail email;

    @JsonProperty("password")
    private MarketPassword password;

    private String city;

    private String town;

    private String detailedAddress;

    private String postalCode;


    @Builder
    public MarketJoinRequestDTO(final MarketName name, final MarketEmail email, final MarketPassword password ,
                                final String city , final String town , final String detailedAddress , final String postalCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.town = town;
        this.detailedAddress = detailedAddress;
        this.postalCode = postalCode;
    }


    public Market toEntity() {
        return Market.builder()
                .name(name)
                .email(email)
                .password(password)
                .locationAddress(LocationAddress.from(city,town,detailedAddress,postalCode))
                .roleType(RoleType.MARKET)
                .build();
    }

}
