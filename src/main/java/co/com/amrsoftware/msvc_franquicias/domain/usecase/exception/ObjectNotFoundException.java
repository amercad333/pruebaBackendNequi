package co.com.amrsoftware.msvc_franquicias.domain.usecase.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
