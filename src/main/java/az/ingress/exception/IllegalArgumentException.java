package az.ingress.exception;

public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String message, Object ...args) {
        super(message.formatted(args));
    }
}
