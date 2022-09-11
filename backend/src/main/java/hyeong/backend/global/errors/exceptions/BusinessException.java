package hyeong.backend.global.errors.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }
}
