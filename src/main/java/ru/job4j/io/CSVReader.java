package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        File file = new File(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String dataReceiver = argsName.get("out");
        String filter = argsName.get("filter");
        String[] filters = filter.split(",");
        try (Scanner scanner = new Scanner(file).useDelimiter(",")) {
            if (!scanner.hasNext()) {
               throw new RuntimeException(String.format("File %s is empty", file.getAbsolutePath()));
            }
            String[] handle = scanner.nextLine().split(delimiter);
            List<Integer> index  = Arrays.stream(filters)
                    .map(s -> Arrays.asList(handle).indexOf(s))
                    .toList();
            if (index.isEmpty()) {
                throw new RuntimeException("Wrong filter");
            }

            List<String> lines = new ArrayList<>();
            lines.add(index.stream().map(i -> handle[i]).collect(Collectors.joining(delimiter)));
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(delimiter);
                lines.add(index.stream().map(i -> line[i]).collect(Collectors.joining(delimiter)));
            }
            if ("stdout".equals(dataReceiver)) {
                lines.forEach(System.out::println);
            } else {
                Files.write(Path.of(dataReceiver), lines);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static  void valid(ArgsName argsName) {
        File file = new File(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String dataReceiver = argsName.get("out");
        String filter = argsName.get("filter");

        if (!file.exists() || file.isDirectory()) {
            throw new RuntimeException("Problem with path");
        }
        if (delimiter.length() != 1) {
            throw  new RuntimeException("Wrong delimiter");
        }
        if (!("stdout".equals(dataReceiver) || new File(dataReceiver).getParentFile().exists())) {
            throw new RuntimeException("Wrong data receiver");
        }
        String[] filters = filter.split(",");
        if (filters.length == 0) {
            throw new RuntimeException("No filters columns");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        valid(argsName);
        handle(argsName);
    }
}