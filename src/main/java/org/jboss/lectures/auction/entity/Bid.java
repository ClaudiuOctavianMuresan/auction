package org.jboss.lectures.auction.entity;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid {
	private User bidder;
	private Auction auction;
	private Long amount;
	private Long id;

	public Bid(User bidder, Auction auction, Long amount) {
		this.bidder = bidder;
		this.auction = auction;
		this.amount = amount;
	}

	@Id
	@GeneratedValue(strategy = AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	public User getBidder() {
		return bidder;
	}

	public Long getAmount() {
		return amount;
	}
	
	@ManyToOne
	public Auction getAuction() {
		return auction;
	}

	public boolean isHigher(Bid bid) {
		if (bid == null) {
			return true;
		}

		return amount > bid.amount;
	}

	public boolean isHigher(Long amount) {
		return this.amount > amount;
	}
}
