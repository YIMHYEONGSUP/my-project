package hyeong.backend.domain.market.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.global.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Market extends BaseTimeEntity {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "market_id")
    private Long id;

    @Embedded
    @Column(name = "market_address" )
    private LocationAddress address;

    @Embedded
    @Column(name = "market_insideCustomerCount")
    private InsideCustomerInfo insideCustomerInfo;

    @Embedded
    @Column(name = "market_outsideCustomerCount")
    private OutSideCustomerInfo outSideCustomerInfo;

    @Embedded
    @Column(name = "market_eventList")
    private EventList eventList;


    @Builder
    public Market (
//            Manager manager,
            LocationAddress address,
            InsideCustomerInfo insideCustomerInfo,
            OutSideCustomerInfo outSideCustomerInfo,
            EventList eventList
    ) {
//        this.manager = manager;
        this.address = address;
        this.insideCustomerInfo = insideCustomerInfo;
        this.outSideCustomerInfo = outSideCustomerInfo;
        this.eventList = eventList;
    }





}
