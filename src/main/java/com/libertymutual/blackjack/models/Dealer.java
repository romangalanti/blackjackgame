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

	public int numberOfCardsLeftInDeck() {
		return deck.numberOfCardsLeftInDeck();
	}
	
	public boolean bust() {
		int[] count = hand.getValues();
		return count[0] > 21 && count[1] > 21;
	}
	
	public void beginRound() {
		hideHoleCard = true;
		hand = new Hand();
	}
	
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
	
	public void dealCardToGambler(Gambler gambler) {
		Card card = deck.getCard();
		if (card != null) {
			gambler.takeCard(card);
		}
	}
	
	public void dealCardToSelf() {
		Card card = deck.getCard();
		if (card != null) {
			hand.addCard(card);
		}
	}
	
	public boolean hasBlackJack() {
		return hand.blackjack();
	}
	
	public int getBestScore() {
		return hand.getHighestValidValue();
	}
	
	public Hand getHand() {
		return hand;
	}

}
