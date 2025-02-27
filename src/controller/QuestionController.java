package controller;

import exceptions.QuestionsFileNotFoundException;
import model.Question;
import service.QuestionService;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<Question> getQuestionsFromService() throws QuestionsFileNotFoundException {
        try {
            return questionService.getQuestions();
        } catch (QuestionsFileNotFoundException e) {
            throw new QuestionsFileNotFoundException(e.getMessage());
        }
    }

    public void addQuestion(Integer questionsListSize) throws IOException {
        try (Scanner sc = new Scanner(System.in).useLocale(Locale.US)) {
            String questionText = sc.nextLine();
            int questionId = questionsListSize + 1;

            questionService.addNewQuestion(questionId, questionText);
        }
    }

    public void deleteQuestion() throws IOException {
        try (Scanner sc = new Scanner(System.in)) {
            int questionId = sc.nextInt();
            questionService.deleteQuestion(questionId);
        } catch (InputMismatchException e) {
            throw new RuntimeException("Please enter a valid question ID to be deleted");
        }
    }
}
