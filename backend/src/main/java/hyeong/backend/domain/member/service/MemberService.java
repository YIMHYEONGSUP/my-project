package hyeong.backend.domain.member.service;

import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    void save(Member member);

    Member findByEmail(MemberEmail email);
}
