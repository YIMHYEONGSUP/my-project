package hyeong.backend.domain.member.Repository;

import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, MemberEmail> , MemberRepositoryCustom{
    Optional<Member> findByMemberEmail(final MemberEmail email);

    boolean existsByMemberEmail(final MemberEmail email);

     void deleteByMemberEmail(final MemberEmail email);
}
