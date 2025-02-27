package dao;

import exceptions.InvalidQuestionFormatException;
import exceptions.QuestionsFileNotFoundException;
import model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    File file = new File("src/resources/forms.txt");

    public List<Question> getQuestionsFromFile() throws QuestionsFileNotFoundException, InvalidQuestionFormatException {
        List<Question> questions = new ArrayList<>();

        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ", 2);

                if (parts.length < 2) {
                    throw new InvalidQuestionFormatException("Invalid question format: %s" + line);
                }

                int questionId = Integer.parseInt(parts[0].trim());
                String questionText = parts[1].trim();

                Question question = new Question(questionId, questionText);

                questions.add(question);
            }
        } catch (IOException e) {
            throw new QuestionsFileNotFoundException("Questions file not found: " + file.getName());
        }

        return questions;
    }

    public void addQuestionToFile(Question question) throws QuestionsFileNotFoundException {
        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("\n" + question.getId() + " - " + question.getText());
            System.out.println("Question successfully added!");
        } catch (IOException e) {
            throw new QuestionsFileNotFoundException(e.getMessage());
        }
    }

    public void deleteQuestionFromFile(Question questionToRemove) throws QuestionsFileNotFoundException {
        List<Question> updatedQuestions = getQuestionsFromFile();

        updatedQuestions.remove(questionToRemove.getId() - 1);

        try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw)) {
            for (Question question : updatedQuestions) {
                bw.write(question.getId() + " - " + question.getText() + "\n");
            }
        } catch (IOException e) {
            throw new QuestionsFileNotFoundException("Questions file not found: " + file.getName());
        }

        System.out.printf("%s: Question successfully deleted!%n", questionToRemove);

    }
}
