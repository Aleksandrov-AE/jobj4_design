package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            int read;
            StringBuilder stringBuilder = new StringBuilder();
            while ((read = in.read()) != -1) {
                stringBuilder.append((char) read);
            }
            String[] split = stringBuilder.toString().split(System.lineSeparator());
            for (String s: split) {
                int num = Integer.parseInt(s);
                boolean result = num % 2 == 0;
                System.out.println(num + " " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
