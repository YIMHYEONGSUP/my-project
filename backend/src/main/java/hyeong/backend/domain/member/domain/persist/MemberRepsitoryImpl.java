package hyeong.backend.domain.member.domain.persist;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.domain.member.domain.vo.UserEmail;
import hyeong.backend.domain.member.domain.vo.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MemberRepsitoryImpl implements MemberRepositoryCustom{

    private JPAQueryFactory query;


}
