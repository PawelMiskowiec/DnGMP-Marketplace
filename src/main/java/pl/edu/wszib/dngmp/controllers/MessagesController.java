package pl.edu.wszib.dngmp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.dngmp.services.IAuctionService;
import pl.edu.wszib.dngmp.services.IMessageService;
import pl.edu.wszib.dngmp.services.IUserService;
import pl.edu.wszib.dngmp.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping("/msng")
@AllArgsConstructor
public class MessagesController {
    private final IMessageService messageService;
    private final IAuctionService auctionService;
    private final IUserService userService;

    @Resource
    SessionObject sessionObject;

    @GetMapping
    public String getAllUsersMessages(Model model){
        model.addAttribute("messages", messageService.getUserMessages());
        model.addAttribute("logged", sessionObject.isLogged());
        return "msng";
    }


}
