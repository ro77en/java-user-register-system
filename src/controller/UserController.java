package controller;

import model.Question;
import model.User;
import service.QuestionService;
import service.UserService;

import java.io.IOException;
import java.util.List;

public class UserController {

    private UserService userService;
    private QuestionService questionService;

    public UserController(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    public void registerUser(List<String> userInputs) throws IOException, IllegalArgumentException {

        String name = userInputs.get(0);
        String email = userInputs.get(1);
        int age = validateAgeFormat(userInputs.get(2));
        float height = validateHeightFormat(userInputs.get(3));

        User user = userService.createUser(name, email, age, height);
        userService.saveUser(user);
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
