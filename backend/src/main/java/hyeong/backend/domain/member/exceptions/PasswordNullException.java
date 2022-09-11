package hyeong.backend.domain.member.exceptions;

import hyeong.backend.global.errors.exceptions.BusinessException;
import hyeong.backend.global.errors.exceptions.ErrorCode;

public class PasswordNullException extends BusinessException {
    public PasswordNullException(ErrorCode errorCode) {
        super(errorCode);
    }
}
