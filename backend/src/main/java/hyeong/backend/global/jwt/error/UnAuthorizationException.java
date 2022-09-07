package hyeong.backend.global.jwt.error;

import hyeong.backend.global.error.exception.BusinessException;
import hyeong.backend.global.error.exception.ErrorCode;

public class UnAuthorizationException extends BusinessException {

    public UnAuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }
}