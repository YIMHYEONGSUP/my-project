package hyeong.backend.global.jwt.error;

import hyeong.backend.global.error.exception.EntityNotFoundException;
import hyeong.backend.global.error.exception.ErrorCode;

public class TokenNotFoundException extends EntityNotFoundException {

    public TokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}