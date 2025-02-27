package controller;


import exceptions.InvalidUserFormatException;
import exceptions.UserSaveException;
import view.QuestionView;
import view.UserView;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class MenuController {

    private UserView userView;
    private QuestionView questionView;


    public MenuController(UserView userView, QuestionView questionView) {
        this.userView = userView;
        this.questionView = questionView;
    }

    public void processMenuInput() throws InputMismatchException, UserSaveException {

        try (Scanner sc = new Scanner(System.in).useLocale(Locale.US)) {
            System.out.print("Enter menu option: ");

            int option = sc.nextInt();
            handleMenuInput(option);

        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input: Please enter an Integer Number");
        }
    }

    public void handleMenuInput(Integer option) throws UserSaveException {
        try {
            switch (option) {
                case 0: {
                    System.out.println("Exiting system...");
                    break;
                }

                case 1: {
                    userView.getNewUserInputs();
                    break;
                }

                case 2: {
                    userView.showAllUsers();
                    break;
                }

                case 3: {
                    questionView.addQuestion();
                    break;
                }

                case 4: {
                    questionView.deleteQuestion();
                    break;
                }

                case 5: {
                    userView.showUsersByName();
                }
            }

        } catch (IOException e) {
            throw new UserSaveException(e.getMessage());
        } catch (InputMismatchException e) {
            throw new InputMismatchException(e.getMessage());
        }


    }
}