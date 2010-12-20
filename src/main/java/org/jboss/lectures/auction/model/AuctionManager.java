package org.jboss.lectures.auction.model;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.lectures.auction.db.DatabaseStub;
import org.jboss.lectures.auction.entity.Auction;
import org.jboss.lectures.auction.entity.User;

@ViewScoped
@Named
public class AuctionManager {
	
	private Auction currentAuction = null;

	@Inject
	private DatabaseStub database;

	@Inject
	private LoginManager loginManager;

	@Produces
	@Dependent
	@Named
	public Auction getCurrentAuction() {
		return currentAuction;
	}

	public Long getCurrentAuctionId() {
		return (currentAuction == null) ? null : currentAuction.getId();
	}

	public void setCurrentAuctionId(Long currentId) {
		this.currentAuction = database.getAuctionById(currentId);
	}

	public List<Auction> getAll() {
		return database.getAllAuctions();
	}
	
	public List<Auction> getAuctionsWinningByUser(User user) {
		return database.getAuctionsWinningByUser(user);
	}
	
	public List<Auction> getAuctionLoosingByUser(User user) {
		return database.getAuctionsLoosingByUser(user);
	}

	public void addAuction(Auction auction) {
		if (!loginManager.isLogged()) {
			throw new IllegalStateException(
					"user must be logged in order to add auction");
		}
		auction.setOwner(loginManager.getCurrentUser());
		currentAuction = database.createAuction(auction);
	}

	@Produces
	@RequestScoped
	@Named("newAuction")
	public Auction createNewAuction() {
		return new Auction();
	}
}
