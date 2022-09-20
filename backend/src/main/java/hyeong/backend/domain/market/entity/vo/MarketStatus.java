package hyeong.backend.domain.market.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import hyeong.backend.global.common.vo.LocationAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


public enum MarketStatus {

    PREPARED , OPEN , CLOSE

}
