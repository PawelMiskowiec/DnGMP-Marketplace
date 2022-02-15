package pl.edu.wszib.dngmp.db;

import pl.edu.wszib.dngmp.model.User;

import java.util.Optional;
import java.util.Set;

public interface IUserDAO {
    Optional<User> getByLogin(String login);

    void addUser(User user);

    Set<User> getUserSetFromMessageByAuctionId(Long id);
}
