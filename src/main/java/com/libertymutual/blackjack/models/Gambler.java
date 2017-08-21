package com.libertymutual.blackjack.models;

import java.util.List;

public class Gambler {
	
	private int cashInWallet;
	private Hand hand;
	
	public Gambler() {
		cashInWallet = 250;
		hand = new Hand();
	}

	//gambler bust criteria
	public boolean bust() {
		int[] count = hand.getValues();
		return count[0] > 21 && count[1] > 21;
	}
	
	//returns cash in wallet
	public int getCashInWallet() {
		return cashInWallet;
	}
	
	//takes bet out of wallet (cashInWaller = cashInWallet - bet)
	public int wager(int bet) {
		hand = new Hand();
		bet = Math.min(bet, cashInWallet);
		cashInWallet -= bet;
		return bet;
	}
	
	//adds card to hand
	public void takeCard(Card card) {
		hand.addCard(card);
	}
	
	//getCard() from hand class
	public List<Card> getCards() {
		return hand.getCards();
	}
	
	//adds payment to wallet: cashInWallet = cashInWallet + cash
	public void payment(int cash) {
		cashInWallet += cash;
	}
	
	//when gambler has blackjack
	public boolean hasBlackJack() {
		return hand.blackjack();
	}
	
	//gets best score that isn't over 21
	public int getBestScore() {
		return hand.getHighestValidValue();
	}
	
	//returns hand value for gambler
	public Hand getHand() {
		return hand;
	}
	
}
