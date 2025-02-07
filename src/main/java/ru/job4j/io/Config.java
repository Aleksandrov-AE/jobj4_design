package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {

        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
           reader.lines()
                   .map(String::trim)
                   .filter(s -> !(s.startsWith("#") || s.isEmpty()))
                   .map(s -> s.split("=", 2))
                   .filter(s -> {
                       if (s.length < 2 || s[0].isEmpty() || s[1].isEmpty()) {
                           throw new IllegalArgumentException("Illegal prop");
                       }
                       return true;
                   })
                   .forEach(s -> this.values.put(s[0], s[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Not value");
        }
       return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));


    }

}