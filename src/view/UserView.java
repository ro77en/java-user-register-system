package view;

import controller.QuestionController;
import controller.UserController;
import model.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserView {

    private UserController userController;
    private QuestionController questionController;

    public UserView(UserController userController, QuestionController questionController) {
        this.userController = userController;
        this.questionController = questionController;
    }

    public void showFormsQuestions() throws IOException, IllegalArgumentException {
        List<String> userInputs = new ArrayList<>();
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        List<Question> questions = questionController.getQuestionsFromService();
        for (Question question : questions) {
            System.out.print(question + " ");
            String input = sc.nextLine();
            userInputs.add(input);
        }

        userController.registerUser(userInputs);

        sc.close();
    }
}
