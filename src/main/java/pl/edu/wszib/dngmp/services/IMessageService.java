package pl.edu.wszib.dngmp.services;

import pl.edu.wszib.dngmp.model.Message;

import java.util.Set;

public interface IMessageService {
    void createMessage(Long id, String message);

    Set<Message> getUserMessages();
}
