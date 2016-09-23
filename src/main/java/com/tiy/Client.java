package com.tiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jessicatracy on 9/23/16.
 */

public class Client {
    public static void main(String[] args) {
        System.out.println("MyClient running...");

        Client myClient = new Client();

        try {
            // connect to server
            Socket clientSocket = new Socket("localhost", 8005);

            // set up input and output streams
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //Ask for the user's name:
            Scanner myScanner = new Scanner(System.in);
//            String userName = myClient.getUserName(myScanner);
//            System.out.println(userName);

            // give server your name
//            out.println(userName);

            boolean keepChatting = true;
            while (keepChatting) {
                // get user's message and send it to the chat room
                System.out.print("Write a message: ");
                String userMessage = myScanner.nextLine();
                String serverResponse = myClient.sendUserMessage(userMessage, in, out);
                System.out.println("Server replied: " + serverResponse);
            }

            // close connection
            clientSocket.close();


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

//    public boolean sendUserMessage(String userMessage, BufferedReader in, PrintWriter out) throws IOException {
//
//        if (userMessage.equalsIgnoreCase("exit")) {
//            return false;
//        }
//        out.println(userMessage);
//        // will have to fix here - make a loop here that stops when we get the end statement.
//        System.out.println("Server's response: " + in.readLine());
//        if (userMessage.equals("history")) {
//            String serverResponse = in.readLine();
//            while (!serverResponse.equals("Tx:History.End")) {
//                System.out.println(serverResponse);
//                serverResponse = in.readLine();
//            }
//        }
////        System.out.println("Server's response: " + serverResponse);
//        return true;
//    }

    public String sendUserMessage(String userMessage, BufferedReader in, PrintWriter out) throws IOException {

        out.println(userMessage);
        // will have to fix here - make a loop here that stops when we get the end statement.
        String serverResponse = in.readLine();

//        System.out.println("Server's response: " + serverResponse);
        return serverResponse;
    }
}

