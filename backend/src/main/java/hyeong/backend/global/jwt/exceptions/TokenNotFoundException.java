package hyeong.backend.global.jwt.exceptions;

import hyeong.backend.global.errors.exceptions.ErrorCode;

import javax.persistence.EntityNotFoundException;

public class TokenNotFoundException extends EntityNotFoundException {

    public TokenNotFoundException(ErrorCode errorCode) {
        super(String.valueOf(errorCode));
    }
}
