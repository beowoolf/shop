package pl.mojezapiski.shop.security.exception;

import pl.mojezapiski.shop.common.exception.BusinessException;

public class RegisterException extends BusinessException {
    public RegisterException(String message) {
        super(message);
    }
}
