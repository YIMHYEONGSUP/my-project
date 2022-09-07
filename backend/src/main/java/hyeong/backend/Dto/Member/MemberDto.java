package hyeong.backend.Dto.Member;

import lombok.*;


@Getter
@ToString
@RequiredArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private Integer age;

    public MemberDto(Long id, String username, Integer age) {
    }

}
