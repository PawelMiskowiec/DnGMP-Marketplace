package pl.edu.wszib.dngmp.services;

import pl.edu.wszib.dngmp.model.Auction;
import pl.edu.wszib.dngmp.model.AuctionCategory;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IAuctionService {
    Set<Auction> getAll();

    void addAuction(Auction auction);

    Optional<Auction> getAuctionById(Long id);

    Set<Auction> getAuctionsByUserId(Long id);

    void updateAuction(Auction auction);

    Set<Auction> getByCategory(AuctionCategory category);
}
