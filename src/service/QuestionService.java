package service;

import dao.QuestionDAO;
import exceptions.QuestionsFileNotFoundException;
import model.Question;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class QuestionService {
    private final QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public List<Question> getQuestions() throws QuestionsFileNotFoundException {
        try {
            return questionDAO.getQuestionsFromFile();
        } catch (QuestionsFileNotFoundException e) {
            throw new QuestionsFileNotFoundException(e.getMessage());
        }
    }

    public void addNewQuestion(Integer id, String text) throws QuestionsFileNotFoundException {
        Question newQuestion = new Question(id, text);
        questionDAO.addQuestionToFile(newQuestion);
    }

    public void deleteQuestion(Integer questionId) throws QuestionsFileNotFoundException {
        if (questionId <= 4) {
            throw new IllegalArgumentException("You can not delete question 4 and/or before");
        }

        List<Question> questions = questionDAO.getQuestionsFromFile();
        Question questionToRemove = null;

        for (Question question : questions) {
            if (questionId == question.getId()) {
                questionToRemove = question;
                break;
            }
        }

        if (questionToRemove == null) {
            throw new NoSuchElementException("Question id not found: " + questionId);
        }

        questionDAO.deleteQuestionFromFile(questionToRemove);
    }
}
