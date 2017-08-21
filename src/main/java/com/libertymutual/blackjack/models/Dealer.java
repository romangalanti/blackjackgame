package com.libertymutual.blackjack.models;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	
	private Hand hand;
	private Deck deck;
	private boolean hideHoleCard;
	
	public Dealer() {
		hand = new Hand();
		deck = new Deck();
		deck.shuffle();
	}
	
	//returns number of cards left in deck from deck class
	public int numberOfCardsLeftInDeck() {
		return deck.numberOfCardsLeftInDeck();
	}
	
	//bust criteria
	public boolean bust() {
		int[] count = hand.getValues();
		return count[0] > 21 && count[1] > 21;
	}
	
	//hides hole card and instantiates a new hand
	public void beginRound() {
		hideHoleCard = true;
		hand = new Hand();
	}
	
	//reveals hole card, hits until dealer gets 17, does not hit if count = 21 or above 17.
	public void endRound() {
		hideHoleCard = false;
		int [] count = hand.getValues();
		
		if (count[0] == 21 || count[1] == 21) {
			return;
		}
		while (count[0] < 17 || count[1] < 17) {
			dealCardToSelf();
			count = hand.getValues();
		}
	}
	
	//holecard hide/add using card list
	public List<Card> getCards() {
		List<Card> cards = hand.getCards();
		if (!hideHoleCard || cards.size() == 0) {
			return cards;
		}
		
		Card firstCard = cards.get(0);
		List<Card> cardsToShow = new ArrayList<Card>();
		cardsToShow.add(firstCard);
		cardsToShow.add(new HoleCard());
		return cardsToShow;
	}
	
	//deals card to gambler
	public void dealCardToGambler(Gambler gambler) {
		Card card = deck.getCard();
		if (card != null) {
			gambler.takeCard(card);
		}
	}
	
	//deals card to dealer
	public void dealCardToSelf() {
		Card card = deck.getCard();
		if (card != null) {
			hand.addCard(card);
		}
	}
	
	//uses blackjack criteria from hand class
	public boolean hasBlackJack() {
		return hand.blackjack();
	}
	
	//used for determining whether or not dealer or gambler has higher hand count
	public int getBestScore() {
		return hand.getHighestValidValue();
	}
	
	//returns hand values
	public Hand getHand() {
		return hand;
	}

}
