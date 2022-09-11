package hyeong.backend.global.jwt.exceptions;

import hyeong.backend.global.errors.exceptions.BusinessException;
import hyeong.backend.global.errors.exceptions.ErrorCode;

public class UnAuthorizationException extends BusinessException {

    public UnAuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
