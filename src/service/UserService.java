package service;

import dao.UserDAO;
import exceptions.InvalidUserFormatException;
import exceptions.UserFileNotFoundException;
import exceptions.UserFileReadException;
import exceptions.UserSaveException;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void saveUser(User user) throws UserSaveException {
        try {
            userDAO.saveUserFile(user);
        } catch (IOException e) {
            throw new UserSaveException("Error saving user: " + e.getMessage());
        }

    }

    public User createUser(String name, String email, Integer age, Float height) throws InvalidUserFormatException, UserFileNotFoundException {
        if (name.trim().length() < 10) {
            throw new InvalidUserFormatException("Name must be 10+ letters long");
        }

        if (!email.contains("@")) {
            throw new InvalidUserFormatException("Invalid e-mail format: " + email);
        }

        if (age < 18 || age > 120) {
            throw new InvalidUserFormatException("User age must be 18+ and less than 120");
        }

        if (height < 0.5 || height > 2.5) {
            throw new InvalidUserFormatException("Height must be between 0.5 and 2.5 meters");
        }

        List<User> userList = getAllUsers();

        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                throw new InvalidUserFormatException("There is already an user with this e-maiL: " + email);
            }
        }

        return new User(name, email, age, height);
    }

    public List<User> getAllUsers() throws UserFileNotFoundException {
        return userDAO.getAllUsersFromFile();
    }

    public List<User> getUsersByName(String name) throws UserFileNotFoundException {
        String[] parts = name.trim().split("\\s");

        List<User> allUsers = getAllUsers();
        List<User> foundUsers = new ArrayList<>();

        for (String part : parts) {
            for (User user : allUsers) {
                if (user.getName().contains(part)) {
                    foundUsers.add(user);
                }
            }
        }

        return foundUsers;
    }
}
