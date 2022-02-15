package pl.edu.wszib.dngmp.validators;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wszib.dngmp.exceptions.AuthValidationException;

public class LoginValidatorTest {
    @Test
    public void tryValidateIncorrectLogin(){
        String login = "kr";
        String expectedResult = "Incorrect login";

        try{
            LoginValidator.validateLogin(login);
        } catch(AuthValidationException e){
            Assert.assertEquals(expectedResult, e.getInfo());
        }
    }

    @Test
    public void tryValidateCorrectLogin(){
        String login = "Dlugi";

        try{
            LoginValidator.validateLogin(login);
        } catch(AuthValidationException e){
            Assert.fail();
        }
    }

    @Test
    public void tryValidateCorrectPassword(){
        String pass = "Haslo";

        try{
            LoginValidator.validatePass(pass);
        } catch(AuthValidationException e){
            Assert.fail();
        }
    }

    @Test
    public void tryValidateincorrectPassword(){
        String pass = "Ho";
        String expectedResult = "Incorrect password";
        try{
            LoginValidator.validatePass(pass);
        } catch(AuthValidationException e){
            Assert.assertEquals(expectedResult, e.getInfo());
        }
    }
}
