package pl.edu.wszib.dngmp.validators;

import pl.edu.wszib.dngmp.exceptions.AuthValidationException;

public class LoginValidator {
    public static void validateLogin(String login){
        if(login == null || login.length() < 4)
            throw new AuthValidationException("Incorrect login");
    }

    public static void validatePass(String pass){
        if(pass == null || pass.length() <4){
            throw new AuthValidationException("Incorrect password");
        }
    }
}
