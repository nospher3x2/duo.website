package dev.duoplugins.website.api.token.exception;

/**
 * @author oNospher
 **/
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String message) {
        super(message);
    }
}
