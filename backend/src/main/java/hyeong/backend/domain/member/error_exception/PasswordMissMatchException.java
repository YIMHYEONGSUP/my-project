package hyeong.backend.domain.member.error_exception;


import hyeong.backend.global.error.exception.BusinessException;
import hyeong.backend.global.error.exception.ErrorCode;

public class PasswordMissMatchException extends BusinessException {

    public PasswordMissMatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}