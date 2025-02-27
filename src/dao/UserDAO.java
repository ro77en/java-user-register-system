package dao;

import exceptions.InvalidUserFormatException;
import exceptions.UserFileNotFoundException;
import exceptions.UserFileReadException;
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

    public List<User> getAllUsersFromFile() throws UserFileNotFoundException {
        List<User> users = new ArrayList<>();

        Path folderPath = Paths.get(USERS_FOLDER_PATH);

        if (!Files.exists(folderPath)) {
            throw new UserFileNotFoundException("Users files folder not found: " + USERS_FOLDER_PATH);
        }

        try (Stream<Path> paths = Files.list(folderPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .sorted(Comparator.comparingLong(path -> path.toFile().lastModified()))
                    .forEach(file -> {
                        try {
                            User user = readUserFromFile(file);
                            users.add(user);
                        } catch (IOException e) {
                            throw new UncheckedIOException(new UserFileNotFoundException("Error reading file: " + file.getFileName()));
                        }
                    });
        } catch (IOException e) {
            throw new UserFileNotFoundException("Error accessing users files folder");
        }
        return users;
    }

    public User readUserFromFile(Path file) throws UserFileReadException, InvalidUserFormatException {

        try {
            List<String> lines = Files.readAllLines(file);

            if (lines.size() < 4) {
                throw new InvalidUserFormatException("Incomplete user file: " + file.getFileName());
            }

            String name = lines.get(0);
            String email = lines.get(1);
            int age = parseInt(lines.get(2));
            float height = parseFloat(lines.get(3));

            return new User(name, email, age, height);
        } catch (IOException e) {
            throw new UserFileReadException("Invalid number format in file: " + file.getFileName());
        }
    }

    public Integer parseInt(String value) throws InvalidUserFormatException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidUserFormatException("Invalid age format: " + e.getMessage());
        }
    }

    public Float parseFloat(String value) throws InvalidUserFormatException {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new InvalidUserFormatException("Invalid height format: " + e.getMessage());
        }
    }
}
