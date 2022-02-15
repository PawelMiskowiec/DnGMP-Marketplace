package pl.edu.wszib.dngmp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuctionValidatorException extends RuntimeException {
    private String info;
}
