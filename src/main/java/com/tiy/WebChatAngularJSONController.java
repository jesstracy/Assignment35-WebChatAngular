package com.tiy;

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

    @RequestMapping(path = "/getMessages.json", method = RequestMethod.GET)
    public List<Message> getAllMessages(String messageText) {
        List<Message> allMessages = new ArrayList<>();
        try {

            String serverResponse = myClient.sendUserMessage(messageText);
            System.out.println(serverResponse);
            Message newMessage = new Message(messageText);
            messages.save(newMessage);
            Iterable<Message> listOfMessages = messages.findAll();
            for (Message message : listOfMessages) {
                allMessages.add(message);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return allMessages;
    }
}
