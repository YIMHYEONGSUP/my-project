package hyeong.backend.domain.member.exceptions;

import hyeong.backend.global.errors.exceptions.BusinessException;
import hyeong.backend.global.errors.exceptions.ErrorCode;

public class DuplicateEmailException extends BusinessException {

    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}