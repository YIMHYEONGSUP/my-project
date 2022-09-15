package hyeong.backend.domain.market.exceptions;

import hyeong.backend.global.errors.exceptions.EntityNotFoundException;
import hyeong.backend.global.errors.exceptions.ErrorCode;

public class MarketNotFoundException extends EntityNotFoundException {

    public MarketNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
