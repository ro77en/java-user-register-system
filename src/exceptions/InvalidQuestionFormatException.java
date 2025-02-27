package exceptions;

public class InvalidQuestionFormatException extends RuntimeException {
    public InvalidQuestionFormatException(String message) {
        super(message);
    }
}
