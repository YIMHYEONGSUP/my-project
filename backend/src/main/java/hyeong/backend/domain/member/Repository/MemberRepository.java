package hyeong.backend.domain.member.Repository;

import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryCustom{
    Member findByEmail(MemberEmail email);
}
