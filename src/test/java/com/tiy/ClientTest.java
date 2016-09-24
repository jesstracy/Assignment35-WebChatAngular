package com.tiy;

import jodd.json.JsonSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by jessicatracy on 9/23/16.
 */
public class ClientTest {
    @Autowired
    MessageRepository messages;

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
        myClient.createNewClientSocket();

        String testText = "test.user.message";
        String testUsername = "test.user.name";
        Message testMessage = new Message(testUsername, testText);
        String testMessageSerialized = jsonSave(testMessage);
        ArrayList<String> serverResponses = myClient.sendUserMessage(testMessageSerialized);
        assertEquals(1, serverResponses.size());
        assertEquals(testUsername + " said: " + testText, serverResponses.get(0));

        //delete testMessage from the db
//        messages.delete(testMessage);
    }

    public String jsonSave(Message messageToSave) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(messageToSave);

        return jsonString;
    }

}