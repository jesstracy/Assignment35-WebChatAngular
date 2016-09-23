package com.tiy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jessicatracy on 9/23/16.
 */
@RestController
public class WebChatAngularJSONController {
    @RequestMapping(path = "/getMessages.json", method = RequestMethod.GET)
}
