package ru.job4j.io;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        valid(args);
        Path start = Paths.get(args[0]);
        String extension = args[1];
        search(start, path -> path.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static void valid(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage  java search dir extension");
        }
        Path path = Paths.get(args[0]);
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IllegalArgumentException("java search dir problem");
        }
        String extension = args[1];
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException("search file extension problem");
        }

    }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}