package pl.edu.wszib.dngmp.model;

import org.apache.commons.lang3.StringUtils;
import pl.edu.wszib.dngmp.exceptions.AuctionValidatorException;

import java.util.Arrays;
import java.util.Optional;

public enum AuctionCategory {
    ELECTRONICS, CARS, SPORTS, TOYS, HOME, GARDEN, TOOLS;
    public static AuctionCategory parseString(String value) {
        return Arrays.stream(values())
                .filter(it -> StringUtils.equalsIgnoreCase(it.name(), value))
                .findFirst()
                .orElseThrow(() -> new AuctionValidatorException("category doesn't exist"));
    }
}
