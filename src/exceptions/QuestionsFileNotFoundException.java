package exceptions;

import java.io.IOException;

public class QuestionsFileNotFoundException extends IOException {
    public QuestionsFileNotFoundException(String message) {
        super(message);
    }
}
