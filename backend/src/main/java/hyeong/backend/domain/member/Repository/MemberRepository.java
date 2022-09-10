package hyeong.backend.domain.member;

import hyeong.backend.domain.member.entity.persist.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryCustom{
}
