package view;


import controller.MenuController;

import java.io.IOException;

public class MenuView {

    private MenuController menuController;

    public MenuView(MenuController menuController) {
        this.menuController = menuController;
    }

    public void showMenu() throws IOException {
        System.out.println("=================== USER SYSTEM ===================");
        System.out.println("Enter the desired option: ");
        System.out.println("0 - Exit System");
        System.out.println("1 - Register new User");
        System.out.println("2 - List all registered Users");
        System.out.println("3 - Add new Question to the form");
        System.out.println("4 - Delete form Question");
        System.out.println("5 - Search User by name, age or e-mail");
        System.out.println("=================== USER SYSTEM ===================");

        menuController.processMenuInput();
    }
}
