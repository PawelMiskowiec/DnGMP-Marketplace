package pl.edu.wszib.dngmp.validators;

import pl.edu.wszib.dngmp.exceptions.AuctionValidatorException;
import pl.edu.wszib.dngmp.exceptions.AuthValidationException;
import pl.edu.wszib.dngmp.model.AuctionCategory;

import java.math.BigDecimal;

public class AuctionValidator {
    public static void validateTitle(String title){
        if(title == null || title.length() < 8){
            throw new AuctionValidatorException("Incorrect login");
        }
    }
    public static void validateDescription(String description){
        if(description == null || description.length() < 20 || description.length() > 300){
            throw new AuctionValidatorException("Incorrect description");
        }
    }
    public static void validatePrice(BigDecimal price){
        if(price == null || price.compareTo(BigDecimal.ZERO) < 0){
            throw new AuctionValidatorException("Incorrect price");
        }
    }
}
