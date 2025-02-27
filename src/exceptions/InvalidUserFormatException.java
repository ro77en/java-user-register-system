package exceptions;

import java.io.IOException;

public class InvalidUserFormatException extends IOException {
    public InvalidUserFormatException(String message) {
        super(message);
    }
}
