package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            return input.lines()
                    .filter(s -> {
                        String[] str = s.split(" ");
                        return str.length > 2 && "404".equals(str[str.length - 2]);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading file" + file);
        }
    }

    public void saveTo(String out) {
        var data = filter();
      try (PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            data.forEach(output::println);
        } catch (IOException e) {
          throw new RuntimeException("Error write file", e);
      }
    }

    public static void main(String[] args) {

        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}

