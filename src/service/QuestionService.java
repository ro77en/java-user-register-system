package service;

import dao.QuestionDAO;
import model.Question;

import java.io.IOException;
import java.util.List;

public class QuestionService {
    private QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public List<Question> getQuestions() throws IOException {
        return questionDAO.getQuestionsFromFile();
    }
}
