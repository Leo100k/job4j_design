package ru.job4j.io;

import java.io.FileOutputStream;

public class Multiple {
    public static void main(String[] args) {

        try (FileOutputStream out = new FileOutputStream("data/multipleResult.txt")) {
            out.write(" 1 * 1 = 1".getBytes());
            out.write("\n 1 * 2 = 2".getBytes());
            out.write("\n 1 * 3 = 3".getBytes());
            out.write("\n 1 * 4 = 4".getBytes());
            out.write("\n 1 * 5 = 5".getBytes());
            out.write("\n 1 * 6 = 6".getBytes());
            out.write("\n 1 * 7 = 7".getBytes());
            out.write("\n 1 * 8 = 8".getBytes());
            out.write("\n 1 * 9 = 9".getBytes());
            out.write("\n 1 * 10 = 10".getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}