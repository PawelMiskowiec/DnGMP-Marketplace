package pl.edu.wszib.dngmp.db;

import pl.edu.wszib.dngmp.model.Message;
import pl.edu.wszib.dngmp.model.User;

import java.util.Set;

public interface IMessageDAO {
    void addMessage(Message message);

    Set<Message> getUserMessages(User user);
}
