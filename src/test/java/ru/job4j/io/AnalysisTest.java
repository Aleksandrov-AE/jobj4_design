package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AnalysisTest {

    @Test
    void unavailableIs(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "300 10:59:01\n"
                    + "500 11:01:02\n"
                    + "200 11:02:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
       Analysis analysis = new Analysis();
       analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
       assertEquals("10:57:01;10:59:01;" + "11:01:02;11:02:02;", result.toString());
    }
    @Test
    void unavailableIsNot(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01\n"
                    + "300 10:57:01\n"
                    + "200 10:58:01\n"
                    + "300 10:59:01\n"
                    + "200 11:01:02\n"
                    + "200 11:02:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertEquals("", result.toString());
    }

    @Test
    void unavailableIsAll(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("500 10:56:01\n"
                    + "500 10:57:01\n"
                    + "500 10:58:01\n"
                    + "500 10:59:01\n"
                    + "400 11:01:02\n"
                    + "500 11:02:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertEquals("", result.toString());
    }
}