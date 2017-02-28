package io.robusta.hand.solution;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import java.util.TreeSet;

import io.robusta.hand.Card;
import io.robusta.hand.CardColor;
import io.robusta.hand.interfaces.IDeck;
import java.util.Random;

public class Deck extends LinkedList<Card> implements IDeck {

	private static final long serialVersionUID = -4686285366508321800L;

	public Deck() {

	}

	

	@Override
	public Card pick() {

		Deck deck = new Deck();
		Collections.shuffle(deck);

		Card card = deck.getFirst();

		deck.remove(card);
		// shuffle;
		// remove card from deck and returns it
		return card;
	}

	@Override
	public TreeSet<Card> pick(int number) {
		// reuse pick()
		return null;
	}

	@Override
	public Hand giveHand() {
		// A hand is a **5** card TreeSet
		return null;
	}

}
