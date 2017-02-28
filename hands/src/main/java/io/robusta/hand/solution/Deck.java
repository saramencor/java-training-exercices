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

		Collections.shuffle(this);

		Card card = this.getFirst();

		this.remove(card);
		// shuffle;
		// remove card from deck and returns it
		return card;
	}

	@Override
	public TreeSet<Card> pick(int number) {
		// reuse pick()
		TreeSet<Card> treeCard = new TreeSet<>();
		for (int i = 0; i < number; i++) {
			treeCard.add(pick());
		}

		return treeCard;
	}

	@Override
	public Hand giveHand() {
		Hand hand =new Hand();
		hand.addAll(pick(5));
		return hand;
	}

}
