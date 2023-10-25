package com.example.studentserver;

import com.example.studentserver.Entity.Student;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@NoArgsConstructor
public class StudentServer {
    @Value("${app.server.port}")
    private int port;
    List<Student> studentList = new ArrayList<>();

    public StudentServer(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(MessageFormat.format("Server is started on port  {0}", port));
            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println(MessageFormat.format("Client connected: {0}", clientSocket));
                Thread clientThread = new Thread(() ->
                {
                    handleClient(clientSocket);
                });
                clientThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String request = reader.readLine();

            System.out.println("client: " + request);
            String response = command(request);


            writer.println(response);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String command(String text) {
        try {
            if (text.split(" ").length == 3) {
                String[] st = text.split(" ");
                int age = Integer.parseInt(st[2]);
                studentList.add(new Student(UUID.randomUUID().toString(), st[0], st[1], age));
                return "Student is created:  firstname: " + st[0] + " lastname: " + st[1] + " age: " + st[2];
            }

            if (text.contains("remove") && !studentList.isEmpty()) {
                text = text.replace("remove ", "");
                String removeId = null;
                for (Student s : studentList) {
                    if (s.getId().equals(text)) {
                        removeId = s.getId();
                        studentList.remove(s);
                        return ("student's removed " + removeId);
                    }
                }

            }
            if (text.equals("info")) {
                if (!studentList.isEmpty()) {
                    StringBuilder g = new StringBuilder();
                    for (Student s : studentList) {
                        g.append(s);
                    }
                    System.out.println(g);
                    return g.toString();
                }
                return "student list is Empty";
            }
            if (text.equals("clear")) {
                studentList.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, but age is Integer";
        }
        return "null";
    }
}