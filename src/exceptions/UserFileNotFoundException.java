package exceptions;

import java.io.IOException;

public class UserFileNotFoundException extends IOException {
    public UserFileNotFoundException(String message) {
        super(message);
    }
}
