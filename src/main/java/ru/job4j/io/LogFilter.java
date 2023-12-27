package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {

            rsl = input.lines()
                    .map(s -> s.split(" "))
                    .filter(s -> s[s.length - 2].equals("404"))
                    .map(s2 -> s2.toString()).toList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach((System.out::println));
    }

}