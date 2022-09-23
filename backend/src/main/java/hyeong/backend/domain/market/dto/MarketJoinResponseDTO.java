package hyeong.backend.domain.market.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.global.common.vo.LocationAddress;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MarketJoinResponseDTO {

    private MarketName name;

    @Builder
    public MarketJoinResponseDTO(final Market market) {
        this.name = market.getName();
    }

    public static MarketJoinResponseDTO from(final Market market) {
        return new MarketJoinResponseDTO(market.getName());
    }
}
