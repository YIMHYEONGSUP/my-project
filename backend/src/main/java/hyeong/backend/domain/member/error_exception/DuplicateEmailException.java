package hyeong.backend.domain.member.error_exception;

import hyeong.backend.global.error.exception.BusinessException;
import hyeong.backend.global.error.exception.ErrorCode;

public class DuplicateEmailException extends BusinessException {

    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
