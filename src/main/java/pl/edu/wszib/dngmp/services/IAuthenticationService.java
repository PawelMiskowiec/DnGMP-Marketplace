package pl.edu.wszib.dngmp.services;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(String login, String password, String email, String phoneNumber);
}
