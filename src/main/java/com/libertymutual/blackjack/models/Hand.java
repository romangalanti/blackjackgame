package com.libertymutual.blackjack.models;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	
	ArrayList<Card> cards;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public int[] getValues() {
		int[] sums = new int[] { 0, 0 };
		
		for (Card c : cards) {
			int[] values = c.getValues();
			sums[0] += values[0];
			sums[1] += values[1];
		}
		
		return sums;
	}
	
	public boolean blackjack() {
		int[] count = getValues();
		return cards.size() == 2 &&
				(count[0] == 21 || count[1] == 21);
	}
	
	public int getHighestValidValue() {
		int[] count = getValues();
		if (count[0] < 21 && count[1] < 21) {
			return Math.max(count[0], count[1]);
		} else if (count[0] < 21) {
			return count[0];
		} else if (count[1] < 21) {
			return count[1];
		}
		return 0;
	}
	
}
