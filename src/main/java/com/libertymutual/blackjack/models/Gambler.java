package com.libertymutual.blackjack.models;

public class Gambler {
	
	private Hand hand;
	
	public Gambler() {
		hand = new Hand();
	}

	public void giveCard(Card cardToDeal) {
		
		hand.addCard(cardToDeal);
		
	}

	

}
