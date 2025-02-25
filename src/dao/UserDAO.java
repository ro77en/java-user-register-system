package dao;

import model.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class UserDAO {

    public void saveUserFile(User user) throws IOException {
        String username = user.getName().toUpperCase().replaceAll("\\s", "");
        File file = new File("src/resources/users/" + getUsersCount() + "-" + username + ".txt");

        try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(user.getName() + "\n");
            bw.write(user.getEmail() + "\n");
            bw.write(String.valueOf(user.getAge()) + "\n");
            bw.write(String.valueOf(user.getHeight()) + "\n");
            System.out.println("User created successfully!");
        }

    }

    public Integer getUsersCount() throws IOException {
        try (Stream<Path> files = Files.list(Paths.get("src/resources/users/"))) {
            int filesCount = (int) files.count();

            return filesCount + 1;
        }
    }
}
