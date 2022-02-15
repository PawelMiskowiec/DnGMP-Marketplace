package pl.edu.wszib.dngmp.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.dngmp.db.IUserDAO;
import pl.edu.wszib.dngmp.model.User;
import pl.edu.wszib.dngmp.services.IUserService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserDAO userDAO;

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Set<User> getUserSetFromMessageByAuctionId(Long id) {
        return userDAO.getUserSetFromMessageByAuctionId(id);
    }
}
