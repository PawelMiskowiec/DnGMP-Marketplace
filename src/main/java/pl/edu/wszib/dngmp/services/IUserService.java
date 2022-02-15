package pl.edu.wszib.dngmp.services;

import pl.edu.wszib.dngmp.model.User;

import java.util.Optional;
import java.util.Set;

public interface IUserService {
    void addUser(User user);
    Optional<User> findUserByLogin(String login);
    Set<User> getUserSetFromMessageByAuctionId(Long id);
}
