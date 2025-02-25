import dao.QuestionDAO;
import model.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            QuestionDAO x = new QuestionDAO();
            List<Question> q = x.getQuestions();

            for (Question y : q) {
                System.out.println(y);
            }

        } catch (IOException e) {
            System.out.printf("Error accessing file: %s", e.getMessage());
        }

    }
}