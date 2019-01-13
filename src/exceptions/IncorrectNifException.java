package exceptions;

public class IncorrectNifException extends Throwable {
    public IncorrectNifException() {
    }

    public IncorrectNifException(String message) {
        super(message);
    }
}
