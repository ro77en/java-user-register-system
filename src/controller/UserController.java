package controller;

import exceptions.InvalidUserFormatException;
import exceptions.UserFileNotFoundException;
import exceptions.UserSaveException;
import model.User;
import service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void registerUser(List<String> userInputs) throws InvalidUserFormatException, UserSaveException, UserFileNotFoundException {

        String name = userInputs.get(0);
        String email = userInputs.get(1);
        int age = validateAgeFormat(userInputs.get(2));
        float height = validateHeightFormat(userInputs.get(3));

        User user = userService.createUser(name, email, age, height);
        userService.saveUser(user);
    }

    public List<User> getUsers() throws IOException {
        return userService.getAllUsers();
    }

    public List<User> searchUsersByName() throws UserFileNotFoundException {
        try (Scanner sc = new Scanner(System.in)){
            System.out.println("Enter a name to search: ");
            String name = sc.nextLine();

            if (name == null) {
                throw new IllegalArgumentException("Please enter a valid name");
            }

            List<User> foundUsers = userService.getUsersByName(name);

            if (foundUsers.isEmpty()) {
                throw new UserFileNotFoundException("No users found");
            }

            return foundUsers;
        }
    }

    public Integer validateAgeFormat(String ageStr) throws NumberFormatException {
        try {
            return Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid inputs: " + e.getMessage());
        }
    }

    public Float validateHeightFormat(String heightStr) throws NumberFormatException {
        try {
            return Float.parseFloat(heightStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid inputs: " + e.getMessage());
        }
    }
}
