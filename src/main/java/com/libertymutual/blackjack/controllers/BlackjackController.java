package com.libertymutual.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.libertymutual.blackjack.models.Dealer;
import com.libertymutual.blackjack.models.Gambler;
import com.libertymutual.blackjack.models.Hand;

@Controller
@RequestMapping("blackjack")
public class BlackjackController {
	
	private Gambler gambler;
	private Dealer dealer;
	private int actualBet;
	
	public BlackjackController() {
		gambler = new Gambler();
		dealer = new Dealer();
	}
	
	@GetMapping("")
	public String blackjack(Model model) {
		if (gambler.bust()) {
			actualBet = 0;
		}
		Hand dealerHand = dealer.getHand();
		Hand gamblerHand = gambler.getHand();
		int numberOfCardsLeftInDeck = dealer.numberOfCardsLeftInDeck();
		model.addAttribute("numberOfCardsLeftInDeck", numberOfCardsLeftInDeck);
		model.addAttribute("gambler", gambler);
		model.addAttribute("dealer", dealer);
		model.addAttribute("gamblerHand", gamblerHand);
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("actualBet", actualBet);
		model.addAttribute("betStatus", actualBet == 0 && gambler.getCashInWallet() > 0);
		model.addAttribute("roundStatus", actualBet != 0 && dealer.numberOfCardsLeftInDeck() > 0);
		return "blackjack/blackjack-form";
	}
	
	@PostMapping("bet")
	public String betMoney(int enteredValue) {
		actualBet = gambler.wager(enteredValue);
		dealer.beginRound();
		dealer.dealCardToGambler(gambler);
		dealer.dealCardToSelf();
		dealer.dealCardToGambler(gambler);
		dealer.dealCardToSelf();
		return "redirect:/blackjack";
	}
	
	@PostMapping("stand")
	public String handleStand() {
		dealer.endRound();
		if (dealer.bust()) {
			gambler.payment(actualBet * 2);
		} else if (gambler.hasBlackJack() && !dealer.hasBlackJack()) {
			gambler.payment(actualBet + actualBet / 2);
		} else if (gambler.getBestScore() == dealer.getBestScore()) {
			gambler.payment(actualBet);
		} else if (gambler.getBestScore() > dealer.getBestScore()) {
			gambler.payment(actualBet * 2);
		}
		
		actualBet = 0;
		return "redirect:/blackjack";
		
	}	
	
	@PostMapping("hit")
	public String hitHand() {
		dealer.dealCardToGambler(gambler);
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
