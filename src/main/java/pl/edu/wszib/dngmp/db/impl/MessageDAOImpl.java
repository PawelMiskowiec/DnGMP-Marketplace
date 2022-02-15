package pl.edu.wszib.dngmp.db.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.dngmp.db.IMessageDAO;
import pl.edu.wszib.dngmp.model.Message;
import pl.edu.wszib.dngmp.model.User;


import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class MessageDAOImpl implements IMessageDAO {
    private final SessionFactory sessionFactory;

    @Override
    public void addMessage(Message message) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(message);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public Set<Message> getUserMessages(User user) {
        Session session = this.sessionFactory.openSession();
        Query<Message> query = session.createQuery("FROM Message WHERE user.id = :id");
        query.setParameter("id", user.getId());
        try{
            Set<Message> messages = query.getResultStream().collect(Collectors.toSet());
            session.close();
            return messages;
        } catch(
        NoResultException e){
            session.close();
            return Collections.emptySet();
        }
    }
}
