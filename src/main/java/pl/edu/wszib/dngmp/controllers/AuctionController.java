package pl.edu.wszib.dngmp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.dngmp.exceptions.AuctionValidatorException;
import pl.edu.wszib.dngmp.model.Auction;
import pl.edu.wszib.dngmp.model.AuctionCategory;
import pl.edu.wszib.dngmp.services.IAuctionService;
import pl.edu.wszib.dngmp.services.IMessageService;
import pl.edu.wszib.dngmp.session.SessionObject;
import pl.edu.wszib.dngmp.validators.AuctionValidator;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class AuctionController {
    private final IAuctionService auctionService;
    private final IMessageService messageService;

    @Resource
    SessionObject sessionObject;

    @GetMapping("/auctions/{id}")
    public String getAuction(@PathVariable Long id, Model model){
        model.addAttribute("auction", this.auctionService.getAuctionById(id).get());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "auctions";
    }

    @PostMapping("/auctions/{id}")
    public String sendMessage(@PathVariable Long id, @RequestParam String message, Model model){
        messageService.createMessage(id, message);
        model.addAttribute("auction", this.auctionService.getAuctionById(id).get());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "auctions" ;
    }

    @GetMapping("/myAuctions")
    public String getUserAuctions(Model model){
        model.addAttribute("auctions", this.auctionService.getAuctionsByUserId(sessionObject.getUser().getId()));
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "myAuctions";
    }



    @GetMapping("/auctions/add")
    public String getAddAuctionForm(){
        return "addAuction";
    }

    @PostMapping("/auctions/add")
    public String addAuction(@RequestParam String title, @RequestParam String category,
                             @RequestParam String description, @RequestParam String price){
        try{
            AuctionValidator.validateTitle(title);
            AuctionValidator.validateDescription(description);
            AuctionValidator.validatePrice(new BigDecimal(price));
            auctionService.addAuction(new Auction(sessionObject.getUser(), title, description,
                    AuctionCategory.parseString(category), new BigDecimal(price)));
        }catch (AuctionValidatorException e){
            return "redirect:/auctions/add";
        }
        return "redirect:myAuctions";
    }

    @GetMapping("/auctions/edit/{id}")
    public String getUpdateAuctionForm(@PathVariable Long id, Model model){
        Auction auction = auctionService.getAuctionById(id).get();
        model.addAttribute("auction", auction);
        return "editAuction";
    }

    @PostMapping("/auctions/edit/{id}")
    public String updateAuction(@PathVariable Long id, @RequestParam String title,
                                @RequestParam String category, @RequestParam String description,
                                @RequestParam String price){
        Auction auction = auctionService.getAuctionById(id).get();
        try{
            AuctionValidator.validateTitle(title);
            AuctionValidator.validateDescription(description);
            AuctionValidator.validatePrice(new BigDecimal(price));
            auction.setCategory(AuctionCategory.parseString(category));
        }catch (AuctionValidatorException e){
            return "redirect:/auctions/edit/" + id;
        }

        auction.setTitle(title);
        auction.setDescription(description);
        auction.setPrice(new BigDecimal(price));

        auctionService.updateAuction(auction);
        return "redirect:/auctions/" + id;
    }
}
