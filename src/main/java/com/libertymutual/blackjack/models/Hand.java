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
	
	public List<Card> getCard() {
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
	
}
