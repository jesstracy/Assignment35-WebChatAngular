package com.tiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jessicatracy on 9/23/16.
 */

public class Client {
    BufferedReader in;
    PrintWriter out;

    public static void main(String[] args) {
        System.out.println("MyClient running...");

        Client myClient = new Client();


        try {
            myClient.createNewClientSocket();

        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public String getUserName(Scanner myScanner) {
        String userName = "name=";
        System.out.print("What is your name? ");
        userName += myScanner.nextLine();
        return userName;
    }

    public void createNewClientSocket() throws IOException {
        // connect to server
        Socket clientSocket = new Socket("localhost", 8005);

        // set up input and output streams
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }


    public ArrayList<String> sendUserMessage(String userMessage) throws IOException {
        out.println(userMessage);
        // will have to fix here - make a loop here that stops when we get the end statement.
        ArrayList<String> allMessages = new ArrayList<>();
        String serverResponse;
        while (!(serverResponse = in.readLine()).equals("TXT::DONE")) {
            System.out.println("*In sendUserMessage* " + serverResponse);
            allMessages.add(serverResponse);
        }
//        System.out.println("Server's response: " + serverResponse);
        return allMessages;
    }
}

