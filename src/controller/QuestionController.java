package controller;

import model.Question;
import service.QuestionService;

import java.io.IOException;
import java.util.List;

public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<Question> getQuestionsFromService() throws IOException {
        return questionService.getQuestions();
    }
}
