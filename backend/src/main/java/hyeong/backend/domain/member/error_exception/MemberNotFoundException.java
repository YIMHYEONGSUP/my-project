package hyeong.backend.domain.member.error_exception;

import hyeong.backend.global.error.exception.EntityNotFoundException;
import hyeong.backend.global.error.exception.ErrorCode;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
