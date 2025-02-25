import controller.QuestionController;
import controller.UserController;
import dao.QuestionDAO;
import dao.UserDAO;
import service.QuestionService;
import service.UserService;
import view.UserView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        QuestionDAO questionDAO = new QuestionDAO();

        UserService userService = new UserService(userDAO);
        QuestionService questionService = new QuestionService(questionDAO);

        UserController userController = new UserController(userService, questionService);
        QuestionController questionController = new QuestionController(questionService);

        UserView userView = new UserView(userController, questionController);

        try {
            userView.showFormsQuestions();
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}