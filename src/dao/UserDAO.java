package dao;

import model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class UserDAO {

    final String USERS_FOLDER_PATH = "src/resources/users/";

    public void saveUserFile(User user) throws IOException {
        String username = user.getName().toUpperCase().replaceAll("\\s", "");
        File file = new File(USERS_FOLDER_PATH + getUsersCount() + "-" + username + ".txt");

        try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(user.getName() + "\n");
            bw.write(user.getEmail() + "\n");
            bw.write(String.valueOf(user.getAge()) + "\n");
            bw.write(String.valueOf(user.getHeight()) + "\n");
            System.out.println("User created successfully!");
        }

    }

    public Integer getUsersCount() throws IOException {
        try (Stream<Path> files = Files.list(Paths.get(USERS_FOLDER_PATH))) {
            int filesCount = (int) files.count();

            return filesCount + 1;
        }
    }

    public List<User> getAllUsersFromFile() throws IOException {
        List<User> users = new ArrayList<>();

        Path folderPath = Paths.get(USERS_FOLDER_PATH);

        try (Stream<Path> paths = Files.list(folderPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .sorted(Comparator.comparingLong(path -> path.toFile().lastModified()))
                    .forEach(file -> {
                        try {
                            User user = readUserFromFile(file);
                            users.add(user);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        }
        return users;
    }

    public User readUserFromFile(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);

        try {
            String name = lines.get(0);
            String email = lines.get(1);
            int age = Integer.parseInt(lines.get(2));
            float height = Float.parseFloat(lines.get(3));

            return new User(name, email, age, height);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid number format in file: " + file.getFileName(), e);
        }
    }
}
