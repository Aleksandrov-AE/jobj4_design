package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, Set<Path>> result = new HashMap<>();
    public  void print() {
        result.entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(e -> {
            System.out.println(e.getKey().getName() + " - " + e.getKey().getSize() / 1000 + "kb");
            e.getValue().forEach(path -> System.out.println(" " + path));
        });
    }
    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (Files.isDirectory(file)) {
            return FileVisitResult.CONTINUE;
        }
        FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());
        if (result.containsKey(fileProperty)) {
            result.get(fileProperty).add(file);
        } else {
            result.put(fileProperty, new HashSet<>());
            result.get(fileProperty).add(file);
        }

        return super.visitFile(file, attributes);
    }

}