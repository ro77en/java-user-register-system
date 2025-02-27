package view;

import controller.QuestionController;
import controller.UserController;
import model.Question;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserView {

    private UserController userController;
    private List<Question> questions;

    public UserView(UserController userController, List<Question> questions) {
        this.userController = userController;
        this.questions = questions;
    }

    public void getNewUserInputs() throws IOException, IllegalArgumentException {
        List<String> userInputs = new ArrayList<>();
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        for (Question question : questions) {
            System.out.print(question + " ");
            String input = sc.nextLine();
            userInputs.add(input);
        }

        userController.registerUser(userInputs);

        sc.close();
    }

    public void showAllUsers() throws IOException {
        List<User> userList = userController.getUsers();
        int i = 1;
        for (User user : userList) {
            System.out.printf("%d - %s%n", i, user.getName());
            i++;
        }
    }
}
