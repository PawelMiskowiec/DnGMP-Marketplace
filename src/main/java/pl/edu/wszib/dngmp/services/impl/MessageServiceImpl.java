package pl.edu.wszib.dngmp.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import pl.edu.wszib.dngmp.db.IAuctionDAO;
import pl.edu.wszib.dngmp.db.IMessageDAO;
import pl.edu.wszib.dngmp.model.Message;
import pl.edu.wszib.dngmp.services.IMessageService;
import pl.edu.wszib.dngmp.services.IUserService;
import pl.edu.wszib.dngmp.session.SessionObject;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements IMessageService {
    private final IMessageDAO messageDAO;
    private final IAuctionDAO auctionDAO;
    private final IUserService userService;

    @Resource
    SessionObject sessionObject;

    @Override
    @Transactional
    public void createMessage(Long auctionId, String message) {
        Message newMessage = new Message(message,
                userService.findUserByLogin(sessionObject.getUser().getLogin()).get(),
                auctionDAO.getOneById(auctionId).get());
        messageDAO.addMessage(newMessage);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Set<Message> getUserMessages() {
        return messageDAO.getUserMessages(sessionObject.getUser());
    }
}
