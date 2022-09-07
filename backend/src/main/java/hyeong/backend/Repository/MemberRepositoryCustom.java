package hyeong.backend.Repository;

import hyeong.backend.Dto.Member.MemberDto;
import hyeong.backend.Dto.Member.MemberSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {

    Page<MemberDto> searchMemberAll(Pageable pageable);
}
