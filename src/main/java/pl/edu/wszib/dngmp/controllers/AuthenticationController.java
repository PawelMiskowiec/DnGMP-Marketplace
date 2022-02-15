package pl.edu.wszib.dngmp.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.dngmp.exceptions.AuthValidationException;
import pl.edu.wszib.dngmp.services.IAuthenticationService;
import pl.edu.wszib.dngmp.session.SessionObject;
import pl.edu.wszib.dngmp.validators.RegisterValidator;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.edu.wszib.dngmp.validators.LoginValidator.validateLogin;
import static pl.edu.wszib.dngmp.validators.LoginValidator.validatePass;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @GetMapping(value = "/login")
    public String getLoginForm() { return "login"; }

    @PostMapping(value = "/login")
    public String login(@RequestParam String login, @RequestParam String password){
        try{
            validateLogin(login);
            validatePass(password);
        } catch (AuthValidationException e){
            return "redirect:/login";
        }

        authenticationService.authenticate(login, password);

        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        } else{
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/reg")
    public String getRegisterForm() { return "reg"; }

    @PostMapping(value = "/reg")
    public String register(@RequestParam String login, @RequestParam String password,
                           @RequestParam String email, @RequestParam String phoneNumber){
        try{
            RegisterValidator.validateLogin(login);
            RegisterValidator.validatePass(login);
            RegisterValidator.validateEmail(email);
            RegisterValidator.validatePhoneNumber(phoneNumber);
        }catch(AuthValidationException e){
            return "redirect:/reg";
        }
        authenticationService.register(login, password, email, phoneNumber);
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        } else{
            return "redirect:/signup";
        }
    }

    @GetMapping(value = "/logout")
    public String logout() {
        this.sessionObject.setUser(null);
        return "redirect:/main";
    }


}
