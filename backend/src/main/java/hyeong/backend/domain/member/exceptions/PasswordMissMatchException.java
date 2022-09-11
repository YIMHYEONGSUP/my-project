package hyeong.backend.domain.member.exceptions;

import hyeong.backend.global.errors.exceptions.BusinessException;
import hyeong.backend.global.errors.exceptions.ErrorCode;

public class PasswordMissMatchException extends BusinessException {

    public PasswordMissMatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
