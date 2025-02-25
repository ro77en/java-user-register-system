package dao;

import model.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    public List<Question> getQuestionsFromFile() throws IOException {
        File file = new File("src/resources/forms.txt");
        List<Question> questions = new ArrayList<>();

        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ", 2);

                if (parts.length < 2) {
                    System.out.printf("Invalid question format: %s", line);
                    continue;
                }

                int questionId = Integer.parseInt(parts[0].trim());
                String questionText = parts[1].trim();

                Question question = new Question(questionId, questionText);

                questions.add(question);
            }
        }

        return questions;
    }
}
