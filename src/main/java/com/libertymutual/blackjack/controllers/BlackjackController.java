package com.libertymutual.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libertymutual.blackjack.models.Card;
import com.libertymutual.blackjack.models.Dealer;
import com.libertymutual.blackjack.models.Deck;
import com.libertymutual.blackjack.models.Gambler;
import com.libertymutual.blackjack.models.Hand;

@Controller
@RequestMapping("blackjack")
public class BlackjackController {
	
	private Deck activeDeck;
	private Dealer dealer;
	private Gambler gambler;
	
	public BlackjackController() {
		
		activeDeck = new Deck();
		dealer = new Dealer();
		gambler = new Gambler();
	}
	
	@GetMapping("")
	public String showCalculatorHomePage(Model model) {
		Hand dealerHand = dealer.getHand();
		Hand gamblerHand = gambler.getHand();
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("gamblerHand", gamblerHand);
		return "blackjack/blackjack-form";
	}
	
	@PostMapping("bet")
	public String betMoney() {
		activeDeck.shuffle();
		Card cardToDeal = activeDeck.getCard();
		gambler.giveCard(cardToDeal);
		cardToDeal = activeDeck.getCard();
		dealer.giveCard(cardToDeal);
		cardToDeal = activeDeck.getCard();
		gambler.giveCard(cardToDeal);
		cardToDeal = activeDeck.getCard();
		dealer.giveCard(cardToDeal);
		return "redirect:/blackjack";
	}
	
	//@PostMapping("deal")
	//public String dealCards() {
	//	return "redirect:/blackjack";
	//}
	
	@PostMapping("stand")
	public String standHand() {
		dealer.finishDealerHand(activeDeck);
		return "redirect:/blackjack";
	}
	
	@PostMapping("hit")
	public String hitHand() {
		Card cardToDeal = activeDeck.getCard();
		gambler.giveCard(cardToDeal);
		return "redirect:/blackjack";
	}
	
	@PostMapping("rules")
	public String showRules() {
		return "blackjack/blackjack-rules";
	}
	
	@PostMapping("rules/home")
	public String rulesHome() {
		return "redirect:/blackjack";
	}
	
}
