import controller.MenuController;
import controller.QuestionController;
import controller.UserController;
import dao.QuestionDAO;
import dao.UserDAO;
import model.Question;
import service.QuestionService;
import service.UserService;
import view.MenuView;
import view.QuestionView;
import view.UserView;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuestionDAO questionDAO = new QuestionDAO();
        QuestionService questionService = new QuestionService(questionDAO);
        QuestionController questionController = new QuestionController(questionService);

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserController userController = new UserController(userService);

        try {
            List<Question> questions = questionController.getQuestionsFromService();

            UserView userView = new UserView(userController, questions);
            QuestionView questionView = new QuestionView(questionController, questions);

            MenuController menuController = new MenuController(userView, questionView);
            MenuView menuView = new MenuView(menuController);

            menuView.showMenu();
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}