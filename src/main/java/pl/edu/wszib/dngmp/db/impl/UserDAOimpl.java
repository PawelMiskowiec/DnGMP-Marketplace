package pl.edu.wszib.dngmp.db.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.dngmp.db.IUserDAO;
import pl.edu.wszib.dngmp.model.User;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class UserDAOimpl implements IUserDAO {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<User> getByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM User WHERE login LIKE :login");
        query.setParameter("login", login);
        try {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        } catch(NoResultException e){
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Set<User> getUserSetFromMessageByAuctionId(Long id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("SELECT user FROM Message WHERE auction.id = :id");
        query.setParameter("id", id);
        try {
            Set<User> users = query.getResultStream().collect(Collectors.toSet());
            session.close();
            return users;
        } catch(NoResultException e){
            session.close();
            return Collections.emptySet();
        }
    }
}
