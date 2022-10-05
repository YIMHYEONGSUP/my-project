package hyeong.backend.global.database;

import hyeong.backend.domain.market.repository.MarketRepository;
import hyeong.backend.domain.member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DbInit {

    private MemberRepository memberRepository;
    private MarketRepository marketRepository;

    @PostConstruct
    public void joinMember() {

    }



}
