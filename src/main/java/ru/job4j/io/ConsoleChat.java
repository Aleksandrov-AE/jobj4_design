package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run()  {
        Random random = new Random();
        List<String> log  = new ArrayList<>();
        List<String> answer =  readPhrases();
        boolean exit = false;
        String line;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            line = scanner.nextLine();
            if (STOP.equals(line)) {
                log.add(line);
               do {
                   line = scanner.nextLine();
                   log.add(line);
               } while (!CONTINUE.equals(line));
            } else {
                if (OUT.equals(line)) {
                    log.add(line);
                    exit = true;
                } else {
                    log.add(line);
                    line = answer.get(random.nextInt(answer.size()));
                    System.out.println(line);
                    log.add(line);
                }
            }
        }
        saveLog(log);

    }

    private List<String> readPhrases()  {
        List<String> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            for (String line: log) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/log.txt", "data/even.txt");
        consoleChat.run();
    }
}