package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "DELETE FROM users");
                statement.execute(sql);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<User> load() throws IOException, IllegalArgumentException {
        List<User> users;
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            users = reader.lines()
                    .map(String::trim)
                    .map(line -> line.split(";", 2))
                    .map(strings -> new User(strings[0], strings[1]))
                    .collect(Collectors.toList());

            for (User us : users) {
                if (us.name.isEmpty() || us.email.isEmpty()) {
                    throw new IllegalArgumentException("null or empty filename");
                }
            }

            return users;
        }
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.deleteAll();
        dataBase.save(dataBase.load());
    }
}