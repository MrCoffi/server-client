package com.example.studentclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Component
public class StudentClient {
    @Value("${app.server.host}")
    private String host;
    @Value("${app.server.port}")
    private int port;

    public void sendToServer(String data) {
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to server " + socket);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(data);
            String input;
            while ((input = reader.readLine()) != null) {
                System.out.println(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "*/3 * * * * *")
    public void send() {
        Scanner s = new Scanner(System.in);
        sendToServer(s.nextLine());
    }
}
