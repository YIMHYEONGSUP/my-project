package hyeong.backend.domain.market.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.member.entity.vo.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "manager_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "market_id")
    private Market market;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", length = 20)
    private RoleType roleType;

    @Embedded
    @Column(name = "manager_id")
    private MemberEmail email;

    @Embedded
    @Column(name = "manager_password")
    private MemberPassword password;

    @Builder
    public Manager(
            MemberEmail email,
            MemberPassword password,
            Market market,
            RoleType roleType
    ) {
        this.email = email;
        this.password = password;
        this.market = market;
        this.roleType = roleType;
    }



    public Manager encode(final PasswordEncoder encoder) {
        password = MemberPassword.encode(password.password(), encoder);
        return this;
    }

}