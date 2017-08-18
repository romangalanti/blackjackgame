package com.libertymutual.blackjack.models;

public class Dealer {
	
	private Hand hand;
	
	public Dealer() {
		hand = new Hand();
	}

	public void giveCard(Card cardToDeal) {
		
		hand.addCard(cardToDeal);
		
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public void finishDealerHand(Deck deck) {
		
		int [] count = hand.getValues();
		
		while (count[0] < 17 || count[1] < 17) {
			Card theNextCard = deck.getCard();
			hand.addCard(theNextCard);
			count = hand.getValues();
		}
		
	}

}
