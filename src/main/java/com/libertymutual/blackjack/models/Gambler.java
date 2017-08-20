package com.libertymutual.blackjack.models;

import java.util.List;

public class Gambler {
	
	private int cashInWallet;
	private Hand hand;
	
	public Gambler() {
		cashInWallet = 250;
		hand = new Hand();
	}

	public boolean bust() {
		int[] count = hand.getValues();
		return count[0] > 21 && count[1] > 21;
	}
	
	public int getCashInWallet() {
		return cashInWallet;
	}
	
	public int wager(int bet) {
		hand = new Hand();
		bet = Math.min(bet, cashInWallet);
		cashInWallet -= bet;
		return bet;
	}
	
	public void takeCard(Card card) {
		hand.addCard(card);
	}
	
	public List<Card> getCards() {
		return hand.getCards();
	}
	
	public void payment(int cash) {
		cashInWallet += cash;
	}
	
	public boolean hasBlackJack() {
		return hand.blackjack();
	}
	
	public int getBestScore() {
		return hand.getHighestValidValue();
	}
	
}
