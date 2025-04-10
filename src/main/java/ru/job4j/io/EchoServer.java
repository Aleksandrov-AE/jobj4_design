package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static final Logger LOG = LoggerFactory.getLogger(EchoServer.class);
    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String request = input.readLine();
                    System.out.println(request);
                    String messege = request.split(" ")[1];
                    Pattern pattern = Pattern.compile("msg=([^&]+)");
                    Matcher matcher = pattern.matcher(messege);

                    if (matcher.find()) {
                        String msgValue = matcher.group(1);
                        if ("Exit".equals(msgValue)) {
                            server.close();
                        } else {
                            if ("Hello".equals(msgValue)) {
                               output.write("Hello\r\n\r\n".getBytes());
                            } else {
                                output.write("What\r\n\r\n".getBytes());
                            }
                        }

                    }
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
                }
            } catch (IOException e) {
            LOG.error("Error ", e);
        }
    }
}