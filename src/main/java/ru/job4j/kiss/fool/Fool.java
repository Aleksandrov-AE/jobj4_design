package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(fizzBuzz(startAt));
            var answer = input.nextLine();
            startAt = getStartAt(startAt, answer);
            startAt++;
        }
    }

    private static int getStartAt(int startAt, String answer) {
        startAt++;
        String aiAnswer = fizzBuzz(startAt);
            if (!aiAnswer.equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
        return startAt;
    }


    private static String fizzBuzz(int startAt) {
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            return  "FizzBuzz";
        } else if (startAt % 3 == 0) {
            return  "Fizz";
        } else if (startAt % 5 == 0) {
            return  "Buzz";
        } else {
            return Integer.toString(startAt);
        }
    }
}
