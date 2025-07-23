package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean access = true;
        String startUnavailable = "";
        String endUnavailable;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("400") || line.startsWith("500")) {
                   if (access) {
                       startUnavailable = line.split(" ")[1];
                   }
                   access = false;
                } else {
                    if (!access) {
                        endUnavailable = line.split(" ")[1];
                        out.println(startUnavailable + ";" + endUnavailable + ";");
                    }
                    access = true;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}