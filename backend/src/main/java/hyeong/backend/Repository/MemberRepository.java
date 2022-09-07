package hyeong.backend.Repository;

import hyeong.backend.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> , MemberRepositoryCustom {
}
