package hyeong.backend.global.jwt.exceptions;

import hyeong.backend.global.errors.exceptions.BusinessException;
import hyeong.backend.global.errors.exceptions.ErrorCode;

public class TokenHasBlackListException extends BusinessException {

    public TokenHasBlackListException(ErrorCode errorCode) {
        super(errorCode);
    }

}
