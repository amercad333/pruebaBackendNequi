package co.com.amrsoftware.msvc_franquicias.domain.usecase.exception;

public class ObjectNotExistingException extends RuntimeException {
    public ObjectNotExistingException(String message) {
        super(message);
    }
}
