package hyeong.backend.Dto.Member;

import lombok.Data;

@Data
public class MemberSearchCondition {

    private Long id;
    private String username;
    private int age;
}
