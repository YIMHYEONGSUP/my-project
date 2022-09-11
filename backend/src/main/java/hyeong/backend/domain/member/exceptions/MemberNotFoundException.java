package hyeong.backend.domain.member.exceptions;

import hyeong.backend.global.errors.exceptions.ErrorCode;

import javax.persistence.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(ErrorCode errorCode) {
        super(String.valueOf(errorCode));
    }
}
