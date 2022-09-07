package hyeong.backend.domain.member.error_exception;

import hyeong.backend.global.error.exception.BusinessException;
import hyeong.backend.global.error.exception.ErrorCode;

public class PasswordNullException extends BusinessException {

    public PasswordNullException(ErrorCode errorCode) {
        super(errorCode);
    }
}