package pl.edu.wszib.dngmp.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.dngmp.db.IAuctionDAO;
import pl.edu.wszib.dngmp.model.Auction;
import pl.edu.wszib.dngmp.model.AuctionCategory;
import pl.edu.wszib.dngmp.services.IAuctionService;
import pl.edu.wszib.dngmp.session.SessionObject;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuctionService implements IAuctionService {
    private final IAuctionDAO auctionDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public Set<Auction> getAll() {
        return auctionDAO.getAll();
    }

    @Override
    @Transactional
    public void addAuction(Auction auction) {
        auctionDAO.addAuction(auction);
    }

    @Override
    public Optional<Auction> getAuctionById(Long id) {
        return auctionDAO.getOneById(id);
    }

    @Override
    @Transactional
    public Set<Auction> getAuctionsByUserId(Long id) {
        Set<Auction> auctionsByUserId = auctionDAO.getAuctionsByUserId(id);
        return auctionsByUserId;
    }

    @Override
    public void updateAuction(Auction auction) {
        auctionDAO.updateAuction(auction);
    }

    @Override
    public Set<Auction> getByCategory(AuctionCategory category) {
        return auctionDAO.getByCategory(category);
    }

}
