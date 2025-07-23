package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                File file = source.toFile();
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void validation(ArgsName argsName) {
        File directory = new File(argsName.get("d"));
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid source directory");
        }
        if (!exclude.startsWith(".")) {
           throw new IllegalArgumentException("Invalid file exception");
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("Wrong extension");
        }
    }


    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        Zip.validation(argsName);
        File directory = new File(argsName.get("d"));
        String exclude = argsName.get("e");
        File target = new File(argsName.get("o"));
        List<Path> source = Search.search(directory.toPath(), path -> path.toFile().getName().endsWith(exclude));
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.packFiles(source, target);
    }
}