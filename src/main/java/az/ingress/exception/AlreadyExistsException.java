package az.ingress.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message, Object ...args) {
        super(message.formatted(args));
    }
}
