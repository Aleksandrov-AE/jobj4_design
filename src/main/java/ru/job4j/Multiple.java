package ru.job4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Multiple {
    public static void main(String[] args) {


        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 1; i < 10; i++) {
                out.write(("1 * " + i + " = " + i).getBytes(StandardCharsets.UTF_8));
                out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
