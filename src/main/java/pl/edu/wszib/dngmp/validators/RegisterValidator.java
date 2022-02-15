package pl.edu.wszib.dngmp.validators;

import pl.edu.wszib.dngmp.exceptions.AuthValidationException;

public class RegisterValidator {
    public static void validateLogin(String login){
        if(login == null || login.length() < 4)
            throw new AuthValidationException("Incorrect login");
    }

    public static void validatePass(String pass){
        if(pass == null || pass.length() <4){
            throw new AuthValidationException("Incorrect password");
        }
    }

    public static void validateEmail(String email){
        if(email == null || email.length() <4){
            throw new AuthValidationException("Incorrect password");
        }
    }

    public static void validatePhoneNumber(String phoneNumber){
        if(phoneNumber == null || phoneNumber.length() <4){
            throw new AuthValidationException("Incorrect password");
        }
    }
}
