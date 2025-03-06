package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter directory path for finding duplicates:");
        String path = scanner.nextLine();
        Files.walkFileTree(Path.of(path), duplicatesVisitor);
        duplicatesVisitor.print();
    }
}