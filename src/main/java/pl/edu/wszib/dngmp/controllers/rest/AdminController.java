package pl.edu.wszib.dngmp.controllers.rest;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.dngmp.model.Auction;
import pl.edu.wszib.dngmp.model.AuctionCategory;
import pl.edu.wszib.dngmp.model.User;
import pl.edu.wszib.dngmp.services.IAuctionService;
import pl.edu.wszib.dngmp.services.IUserService;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final IAuctionService auctionService;
    private final IUserService userService;

    @GetMapping("/initialization")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void initialize(){
        addExampleUsers();
        addExampleAuctions();
    }

    private void addExampleUsers() {
        userService.addUser(new User(
                "admin", DigestUtils.md5Hex("admin"), "admin@adm.pl", "12353443"
        ));
        userService.addUser(new User(
                "user", DigestUtils.md5Hex("user"), "user@usr.pl", "123521443"
        ));
    }

    private void addExampleAuctions(){
        auctionService.addAuction(new Auction(
            userService.findUserByLogin("admin").get(), "Ogloszenie 1",
                "Opis wspanialego przedmiotu na sprzedaz na tym cudownym portalu",
                AuctionCategory.ELECTRONICS, new BigDecimal("102")
        ));
        auctionService.addAuction(new Auction(
                userService.findUserByLogin("user").get(), "Aukcja 2",
                "Opis wspanialego przedmiotu na sprzedaz na tym cudownym portalu drugiego nawet mozna powiedziec",
                AuctionCategory.ELECTRONICS, new BigDecimal("152")
        ));
    }
}
