package com.tiy;

import jodd.json.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jessicatracy on 9/23/16.
 */

public class ConnectionHandler implements Runnable {
    Socket clientSocket;
    Server myServer;


    public ConnectionHandler(Socket clientSocket, Server myServer) {
        this.clientSocket = clientSocket;
        this.myServer = myServer;
    }

    public void run() {
        try {
            handleIncomingConnection(clientSocket);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //Instead I'm going to make it receive json message, deserialize it, (save it to db), and send back a list of all messages (from arraylist now, db later?)
    public void handleIncomingConnection(Socket clientSocket) throws IOException {

        System.out.println("Now displaying info about who has connected to our server: ");
        System.out.println("Connection from " + clientSocket.getInetAddress().getHostAddress());

        //Read in info from client
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //Print output to client
        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
//        while((inputLine = inputFromClient.readLine()) != null) {
//            if (!inputLine.equals("history")) {
//                System.out.println("Received message \"" + inputLine + "\" from " + clientSocket.toString());
//                outputToClient.println(": " + inputLine);
//                myServer.addToAllMessages(inputLine);
//            } else {
//                outputToClient.println("Now printing history!");
//                for (String message : myServer.allMessages) {
////                    System.out.println("Now printing history!");
//                    outputToClient.println(message);
//                    System.out.println(message);
//                }
//                outputToClient.println("HISTORY::END.");
//
//            }
//        }

        while((inputLine = inputFromClient.readLine()) != null) {
            System.out.println("Received message \"" + inputLine + "\" from " + clientSocket.toString());
            Message currentMessage = jsonRestore(inputLine);
            //Saving to db not working!!! :-(
//            myServer.saveMessageToDB(currentMessage);
            String messageContent = currentMessage.getName() + " said: " + currentMessage.getText();
            myServer.addToAllMessages(messageContent);
            for (String message : myServer.allMessages) {
                outputToClient.println(message);
            }
            outputToClient.println("TXT::DONE");
        }
    }

    public Message jsonRestore(String jsonTD) {
        JsonParser toDoItemParser = new JsonParser();
        Message message = toDoItemParser.parse(jsonTD, Message.class);

        return message;
    }

}
