package hyeong.backend.global.errors.exceptions;

public class InvalidException extends BusinessException{

    public InvalidException(ErrorCode errorCode) {
        super(errorCode);
    }
}
