package hyeong.backend.global.error.exception;

public class InvalidException extends BusinessException{

    public InvalidException(ErrorCode errorCode) {
        super(errorCode);
    }
}
