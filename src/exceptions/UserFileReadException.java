package exceptions;

import java.io.IOException;

public class UserFileReadException extends IOException {
    public UserFileReadException(String message) {
        super(message);
    }
}
