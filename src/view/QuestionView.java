package view;

import controller.QuestionController;
import model.Question;

import java.io.IOException;
import java.util.*;

public class QuestionView {

    private QuestionController questionController;
    private List<Question> questions;

    public QuestionView(QuestionController questionController, List<Question> questions) {
        this.questionController = questionController;
        this.questions = questions;
    }

    public void printFormQuestions() throws IOException {
        this.questions = questionController.getQuestionsFromService();
        System.out.println("Form questions: ");
        for (Question question : questions) {
            System.out.println(question);
        }
    }

    public void addQuestion() throws IOException, InputMismatchException {
        try (Scanner sc = new Scanner(System.in).useLocale(Locale.US)) {
            System.out.println("Current questions in forms: ");
            printFormQuestions();

            System.out.print("\nEnter new question to be added: ");

            questionController.addQuestion(questions.size());

            System.out.println("Updated forms: ");
            printFormQuestions();
        }
    }

    public void deleteQuestion() throws IOException {
        System.out.println("Current questions in forms: ");
        printFormQuestions();

        System.out.println("Enter question to be deleted: ");
        questionController.deleteQuestion();
        printFormQuestions();
    }
}
