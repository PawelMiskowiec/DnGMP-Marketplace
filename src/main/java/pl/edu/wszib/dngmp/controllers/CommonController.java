package pl.edu.wszib.dngmp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.dngmp.model.AuctionCategory;
import pl.edu.wszib.dngmp.services.IAuctionService;
import pl.edu.wszib.dngmp.session.SessionObject;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class CommonController {
    private final IAuctionService auctionService;

    @Resource
    SessionObject sessionObject;

    @GetMapping
    public String getMain() {
        return "redirect:/main";
    }

    @GetMapping(value="/main")
    public String main(Model model, @RequestParam Optional<AuctionCategory> category){
        model.addAttribute("cat", Set.of(AuctionCategory.values()));
        model.addAttribute("logged", this.sessionObject.isLogged());
        if(category.isPresent()){
            model.addAttribute("auctions", this.auctionService.getByCategory(category.get()));
        } else{
            model.addAttribute("auctions", this.auctionService.getAll());
        }

        return "main";
    }
}
