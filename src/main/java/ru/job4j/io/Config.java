package ru.job4j.io;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {

         values.putAll(reader.lines()
                    .map(String::trim)
                    .filter(s -> s.contains("="))
                    .filter(this::validate)
                    .map(line -> line.split("=", 2))
                    .collect(Collectors.toMap(
                            e -> e[0],
                            e -> e[1],
                            (first, second) -> "%s+%s".formatted(first, second)
                    )));

                            } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String value(String key) {
        return values.get(key);

    }

    private boolean validate(String name) {
        System.out.println("Validate" + name);
            if (name.startsWith("=")) {
            throw new IllegalArgumentException(
                    "this name: %s does not contain a key".formatted(name));
        }
        if (name.indexOf("=") == name.length() - 1) {
            throw new IllegalArgumentException(
                    "this name: %s does not contain a value".formatted(name));
        }
        return true;
    }





    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/pair_without_comment.properties"));
    }

}