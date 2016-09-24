package com.tiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by jessicatracy on 9/23/16.
 */

public class Server {
//    MyServer myServer = new MyServer();
    public static void main(String[] args) {
        Server myServer = new Server();
        myServer.startServer();
    }

    ArrayList<String> allMessages = new ArrayList<>();

    public void startServer() {
        try {
            System.out.println("Server running!");
            ServerSocket serverListener = new ServerSocket(8005);
            System.out.println("Waiting for a connection...");

            while (true) {
                Socket clientSocket = serverListener.accept();
                System.out.println("Connection found!");
                //start Thread on this connection
                ConnectionHandler myHandler = new ConnectionHandler(clientSocket, this);
//                handleIncomingConnection(clientSocket);
                Thread newHandlingThread = new Thread(myHandler);
                newHandlingThread.start();
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void addToAllMessages(String messageContent) {
        allMessages.add(messageContent);
    }


}
