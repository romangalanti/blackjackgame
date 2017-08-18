package com.libertymutual.blackjack.models;

public class Dealer {
	
	private Hand hand;
	
	public Dealer() {
		hand = new Hand();
	}

	public void giveCard(Card cardToDeal) {
		
		hand.addCard(cardToDeal);
		
	}

}
