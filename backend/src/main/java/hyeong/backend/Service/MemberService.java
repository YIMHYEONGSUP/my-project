package hyeong.backend.Service;

import hyeong.backend.Dto.Member.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    public void save(String username , int age);
    public Optional<MemberDto> find (Long id);

    public Page<MemberDto> findAll(Pageable pageable);
}
