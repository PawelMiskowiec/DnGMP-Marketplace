package pl.edu.wszib.dngmp;

import pl.edu.wszib.dngmp.model.Auction;
import pl.edu.wszib.dngmp.model.AuctionCategory;
import pl.edu.wszib.dngmp.model.User;

import java.math.BigDecimal;
import java.util.Optional;

public class TestUtils {
    public static Optional<User> createTestUser(){
        return Optional.of(new User("user", "ee11cbb19052e40b07aac0ca060c23ee",
                "example@mail.com", "7123213543"));
    }

    public static Auction createTestAuction(){
        return new Auction(createTestUser().get(), "Ogloszenie numer 1", "Opis ogloszenia numer 1  nie za dlugi nie za krotki",
                AuctionCategory.TOOLS, BigDecimal.TEN);
    }
}
