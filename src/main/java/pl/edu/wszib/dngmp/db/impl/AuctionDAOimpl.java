package pl.edu.wszib.dngmp.db.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.dngmp.db.IAuctionDAO;
import pl.edu.wszib.dngmp.model.Auction;
import pl.edu.wszib.dngmp.model.AuctionCategory;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class AuctionDAOimpl implements IAuctionDAO {
    private final SessionFactory sessionFactory;

    @Override
    public Set<Auction> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Auction> query = session.createQuery("FROM Auction ORDER BY createdAt DESC");
        try {
            Set<Auction> auctions= query.getResultStream().collect(Collectors.toSet());
            session.close();
            return auctions;
        } catch(NoResultException e){
            session.close();
            return Collections.emptySet();
        }
    }

    @Override
    public void addAuction(Auction auction) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(auction);
            tx.commit();
        } catch(Exception e){
            if(tx!=null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Auction> getOneById(Long id) {
        Session session = this.sessionFactory.openSession();
        Query<Auction> query = session.createQuery("FROM Auction WHERE id = :id");
        query.setParameter("id", id);
        try {
            Auction auction= query.getSingleResult();
            session.close();
            return Optional.of(auction);
        } catch(NoResultException e){
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public Set<Auction> getAuctionsByUserId(Long id) {
        Session session = this.sessionFactory.openSession();
        Query<Auction> query = session.createQuery("FROM Auction WHERE user.id = :id");
        query.setParameter("id", id);
        try {
            Set<Auction> auctions= query.getResultStream().collect(Collectors.toSet());
            session.close();
            return auctions;
        } catch(NoResultException e){
            session.close();
            return Collections.emptySet();
        }
    }

    @Override
    public void updateAuction(Auction auction) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(auction);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Set<Auction> getByCategory(AuctionCategory category) {
        Session session = this.sessionFactory.openSession();
        Query<Auction> query = session.createQuery("FROM Auction WHERE category = :category ORDER BY createdAt DESC");
        query.setParameter("category", category);
        try {
            Set<Auction> auctions= query.getResultStream().collect(Collectors.toSet());
            session.close();
            return auctions;
        } catch(NoResultException e){
            session.close();
            return Collections.emptySet();
        }
    }
}
