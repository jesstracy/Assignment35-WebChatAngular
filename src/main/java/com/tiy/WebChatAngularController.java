package com.tiy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by jessicatracy on 9/23/16.
 */
@Controller
public class WebChatAngularController {
    boolean hasUser = false;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        if (hasUser) {
            model.addAttribute("username", session.getAttribute("username"));
        }
        return "home";
    }

    @RequestMapping(path = "/setUsername", method = RequestMethod.POST)
    public String setUsername(HttpSession session, String username) {
        session.setAttribute("username", username);
        hasUser = true;
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
