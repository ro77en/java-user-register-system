package controller;


import view.UserView;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class MenuController {

    private UserView userView;


    public MenuController(UserView userView) {
        this.userView = userView;
    }

    public void processMenuInput() throws InputMismatchException, IOException {

        try (Scanner sc = new Scanner(System.in).useLocale(Locale.US)) {
            System.out.print("Enter menu option: ");

            int option = sc.nextInt();
            handleMenuInput(option);

        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input: Please enter an Integer Number");
        }
    }

    public void handleMenuInput(Integer option) throws IOException {
        try {
            switch (option) {
                case 0: {
                    System.out.println("Exiting system...");
                    break;
                }

                case 1: {
                    userView.showFormsQuestions();
                    break;
                }

                case 2: {
                    userView.showAllUsers();
                }

                case 3: {

                }
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }


    }
}