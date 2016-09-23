package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jessicatracy on 9/23/16.
 */
@RestController
public class WebChatAngularJSONController {
    @Autowired
    MessageRepository messages;

    @RequestMapping(path = "/getMessages.json", method = RequestMethod.GET)
    public List<Message> getAllMessages(String messageText) {
        Message newMessage = new Message(messageText);
        messages.save(newMessage);
        Iterable<Message> listOfMessages = messages.findAll();
        List<Message> allMessages = new ArrayList<>();
        for (Message message : listOfMessages) {
            allMessages.add(message);
        }
        return allMessages;
    }
}
