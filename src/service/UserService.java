package service;

import dao.UserDAO;
import model.User;

import java.io.IOException;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void saveUser(User user) throws IOException {
        userDAO.saveUserFile(user);
    }

    public User createUser(String name, String email, Integer age, Float height) throws IllegalArgumentException {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age must be between 0 and 120");
        }

        if (height < 0.5 || height > 2.5) {
            throw new IllegalArgumentException("Height must be between 0.5 and 2.5 meters");
        }

        return new User(name, email, age, height);
    }

    public List<User> getAllUsers() throws IOException {
        return userDAO.getAllUsersFromFile();
    }
}
