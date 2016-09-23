package com.tiy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by jessicatracy on 9/23/16.
 */
public class ClientTest {
    @Before
    public void setUp() throws Exception {
//        Server myServer = new Server();
//        myServer.startServer();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sendUserMessageTest() throws Exception {
        Client myClient = new Client();
        Socket clientSocket = new Socket("localhost", 8005);

        // set up input and output streams
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String testMessage = "test.user.message";
        String serverResponse = myClient.sendUserMessage(testMessage);
        assertEquals("Echo: test.user.message", serverResponse);

    }

}