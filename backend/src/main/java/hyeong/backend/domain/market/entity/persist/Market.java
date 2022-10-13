package hyeong.backend.domain.market.entity.persist;

import hyeong.backend.domain.event.entity.persist.Event;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.order.entity.persist.Orders;
import hyeong.backend.global.common.vo.RoleType;
import hyeong.backend.global.common.vo.LocationAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Market {

    @Id
    @Embedded
    @Column(name = "market_email" , unique = true , nullable = false )
    private MarketEmail marketEmail;

    @Embedded
    @Column(name = "market_password")
    private MarketPassword marketPassword;

    @Embedded
    @Column(name = "market_name")
    private MarketName marketName;

    @Embedded
    @Column(name = "market_location_address")
    private LocationAddress marketLocationAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "market_role_type", length = 20)
    private RoleType marketRoleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "market_status")
    private MarketStatus marketStatus;

    @OneToMany(mappedBy = "market" , cascade = CascadeType.ALL)
    @Column(name = "market_item")
    private List<Item> itemList;

    @OneToMany(mappedBy = "market" , cascade = CascadeType.ALL)
    @Column(name = "market_order")
    private List<Orders> orderList;

    @OneToMany(mappedBy = "market")
    @Column(name = "market_reviews")
    private List<Review> reviews;

    @OneToMany(mappedBy = "market")
    @Column(name = "market_eventList")
    private List<Event> eventList;


/*  테이블
    @Embedded
    @Column(name = "market_inside_customer_info")
    private InsideCustomerInfo insideCustomerInfo;

    @Embedded
    @Column(name = "market_outside_customer_info")
    private OutSideCustomerInfo outSideCustomerInfo;
*/

    @Builder
    public Market(
            final RoleType marketRoleType, final MarketStatus marketStatus,
           final MarketName marketName,
            final MarketEmail marketEmail, final MarketPassword marketPassword,
            final LocationAddress marketLocationAddress, final List<Review> reviews,
            final List<Event> events
    ) {
        this.marketRoleType = marketRoleType;
        this.marketStatus = marketStatus;
        this.marketName = marketName;
        this.marketEmail = marketEmail;
        this.marketPassword = marketPassword;
        this.marketLocationAddress = marketLocationAddress;
        this.reviews = reviews;
        this.eventList = events;
    }


    public Market update(final Market market, final PasswordEncoder encoder) {
        changeEmail(market.marketEmail);
        changePassword(market.marketPassword);
        changeNickName(market.marketName);
        encode(encoder);
        return this;
    }

    private void init(){

    }

    private void changeEmail(MarketEmail email) {
        this.marketEmail = email;
    }

    private void changePassword(MarketPassword password) {
        this.marketPassword = password;
    }

    private void changeNickName(MarketName name) {
        this.marketName = name;
    }

    public void changeStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
    }


    public Market encode(final PasswordEncoder encoder) {
        marketPassword = MarketPassword.encode(marketPassword.password(), encoder);
        return this;
    }

}
