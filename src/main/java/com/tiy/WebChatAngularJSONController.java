package com.tiy;


import jodd.json.JsonSerializer;
import jodd.json.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jessicatracy on 9/23/16.
 */
@RestController
public class WebChatAngularJSONController {
    @Autowired
    MessageRepository messages;

    Client myClient = new Client();

//    @RequestMapping(path = "/getMessages.json", method = RequestMethod.GET)
//    public List<Message> getAllMessages(String messageText) {
//        List<Message> allMessages = new ArrayList<>();
//        try {
//            String serverResponse = myClient.sendUserMessage(messageText);
//            System.out.println(serverResponse);
//            Message newMessage = new Message(messageText);
//            messages.save(newMessage);
//            Iterable<Message> listOfMessages = messages.findAll();
//            for (Message message : listOfMessages) {
//                allMessages.add(message);
//            }
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//        return allMessages;
//    }

    @RequestMapping(path = "/sendMessage.json", method = RequestMethod.GET)
    public ArrayList<String> sendMessageJson(String username, String messageText) {
        ArrayList<String> listOfMessages = new ArrayList<>();
        String serverResponse = null;
        try {
            myClient.createNewClientSocket();
            Message newMessage = new Message(username, messageText);
            //serialize message
            String serializedMessage = jsonSave(newMessage);
            //send to server -- method
            listOfMessages = myClient.sendUserMessage(serializedMessage);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listOfMessages;
    }

    public String jsonSave(Message messageToSave) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(messageToSave);

        return jsonString;
    }

}
