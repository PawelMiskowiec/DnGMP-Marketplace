package pl.edu.wszib.dngmp.services.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import pl.edu.wszib.dngmp.db.IUserDAO;
import pl.edu.wszib.dngmp.model.User;
import pl.edu.wszib.dngmp.services.IAuthenticationService;
import pl.edu.wszib.dngmp.session.SessionObject;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    @Transactional
    public void authenticate(String login, String password) {
        Optional<User> user = userDAO.getByLogin(login);

        if(user.isPresent() && user.get().getPassword().equals(DigestUtils.md5Hex(password))){
            this.sessionObject.setUser(user.get());
        }
    }

    @Override
    @Transactional
    public void register(String login, String password, String email, String phoneNumber) {
        userDAO.addUser(new User(login, password, email, phoneNumber));
        Optional<User> user = userDAO.getByLogin(login);
        user.ifPresent(value -> this.sessionObject.setUser(value));
    }
}
