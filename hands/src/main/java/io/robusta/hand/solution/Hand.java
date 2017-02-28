package io.robusta.hand.solution;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.GroupLayout;

import io.robusta.hand.Card;
import io.robusta.hand.CardColor;
import io.robusta.hand.HandClassifier;
import io.robusta.hand.HandValue;
import io.robusta.hand.interfaces.IDeck;
import io.robusta.hand.interfaces.IHand;
import io.robusta.hand.interfaces.IHandResolver;

public class Hand extends TreeSet<Card> implements IHand {

	private static final long serialVersionUID = 7824823998655881611L;

	@Override
	public Set<Card> changeCards(IDeck deck, Set<Card> cards) {

		// For exemple remove three cards from this hand
		// , and get 3 new ones from the Deck
		// returns the new given cards
		return null;
	}

	@Override
	public boolean beats(IHand villain) {

		return false;
	}

	@Override
	public IHand getHand() {
		return this;
	}

	@Override
	public HandClassifier getClassifier() {

		return this.getValue().getClassifier();
	}

	@Override
	public boolean isStraight() {
		List<Card> cards = new ArrayList<>();

		cards.addAll(this);
		for (int i = 1; i < 5; i++) {
			if (cards.get(i).getValue() - cards.get(i - 1).getValue() != 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isFlush() {
		List<Card> cards = new ArrayList<>();

		cards.addAll(this);
		for (int i = 1; i < 5; i++) {
			if (cards.get(i).getColor() != cards.get(i - 1).getColor()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns number of identical cards 5s5cAd2s3s has two cardValue of 5
	 */
	@Override
	public int number(int cardValue) {
		int result = 0;
		for (Card current : this) {
			if (current.getValue() == cardValue) {
				result++;
			}
		}
		return result;
	}

	/**
	 * The fundamental map Check the tests and README to understand
	 */
	@Override
	public HashMap<Integer, List<Card>> group() {
		HashMap<Integer, List<Card>> map = new HashMap<>();

		for (Card c : this) {
			c.getValue();
			c.getColor();
			if (map.get(c.getValue()) == null) {
				List<Card> list = new ArrayList<>();
				list.add(c);
				map.put(c.getValue(), list);
			} else {

				List<Card> listC = new ArrayList<>();
				listC = map.get(c.getValue());
				listC.add(c);
				map.put(c.getValue(), listC);

			}
		}

		return map;
	}

	// different states of the hand
	int mainValue;
	int tripsValue;
	int pairValue;
	int secondValue;
	TreeSet<Card> remainings;

	/**
	 * return all single cards not used to build the classifier
	 * 
	 * @param map
	 * @return
	 */
	TreeSet<Card> getGroupRemainingsCard(Map<Integer, List<Card>> map) {
		TreeSet<Card> groupRemaining = new TreeSet<>();
		// May be adapted at the end of project:
		// if straight or flush : return empty
		// If High card, return 4 cards

		for (List<Card> group : map.values()) {
			if (group.size() == 1) {
				groupRemaining.add(group.get(0));
			}
		}
		return groupRemaining;
	}

	@Override
	public boolean isPair() {
		List<Card> cards = new ArrayList<>();
		cards.addAll(this);
		HashMap<Integer, List<Card>> group = group();
		int taille = group.size();
		if (taille == 4) {

			for (List<Card> row : group.values()) {
				if (row.size() == 2) {
					this.mainValue = row.get(0).getValue();
					return true;

				}
			}
		}

		return false;

	}

	@Override
	public boolean isDoublePair() {

		List<Card> cards = new ArrayList<>();
		cards.addAll(this);
		HashMap<Integer, List<Card>> group = group();
		int taille = group.size();
		List<Integer> cardHigher = new ArrayList<>();
		if (taille == 2) {
			for (List<Card> row : group.values()) {
				if (row.size() == 2) {
					cardHigher.add(row.get(0).getValue());
				}

				if (row.size() == 3) {
					return false;
				}
			}
			if (cardHigher.get(0) > cardHigher.get(1)) {
				this.mainValue = cardHigher.get(0);
				this.secondValue = cardHigher.get(1);
			} else {
				this.mainValue = cardHigher.get(1);
				this.secondValue = cardHigher.get(0);
			}
		}
		return true;
	}

	@Override
	public boolean isHighCard() {
		List<Card> cards = new ArrayList<>();
		cards.addAll(this);
		HashMap<Integer, List<Card>> group = group();
		int taille = group.size();
		if (taille == 5) {
		return true;
		}
		return false;
	}

	@Override
	public boolean isTrips() {

		List<Card> cards = new ArrayList<>();
		cards.addAll(this);
		HashMap<Integer, List<Card>> group = group();
		int taille = group.size();

		if (taille == 3) {
			for (List<Card> row : group.values()) {
				if (row.size() > 2) {
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public boolean isFourOfAKind() {

		List<Card> cards = new ArrayList<>();
		cards.addAll(this);
		HashMap<Integer, List<Card>> group = group();
		int taille = group.size();

		if (taille == 2) {
			for (List<Card> row : group.values()) {
				if (row.size() == 1) {
					this.secondValue=row.get(0).getValue();
				}
				if (row.size() == 4) {
					this.mainValue=row.get(0).getValue();
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public boolean isFull() {
		List<Card> cards = new ArrayList<>();
		cards.addAll(this);
		HashMap<Integer, List<Card>> group = group();
		int taille = group.size();

		if (taille == 2) {
			for (List<Card> row : group.values()) {
				if (row.size() == 2) {
					this.secondValue = row.get(0).getValue();
				} else if (row.size() == 3) {
					this.mainValue = row.get(0).getValue();
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public boolean isStraightFlush() {
		List<Card> cards = new ArrayList<>();
		cards.addAll(this);

		if ((isFlush() == true) && (isStraight() == true)) {
			return true;
		}

		return false;
	}

	@Override
	public HandValue getValue() {
		HandValue handValue = new HandValue();
		HashMap<Integer, List<Card>> group = group();
		handValue.setOtherCards(this.getGroupRemainingsCard(group));
		// Exemple for FourOfAKind ; // do for all classifiers
		if (this.isFourOfAKind()) {
			handValue.setClassifier(HandClassifier.FOUR_OF_A_KIND);
			handValue.setLevelValue(this.mainValue);// or this.getRemainings()
			return handValue;
		}
		if (this.isStraightFlush()) {
			handValue.setClassifier(HandClassifier.STRAIGHT_FLUSH);
			handValue.setLevelValue(this.last().getValue());

			return handValue;
		}
		if (this.isStraight()) {
			handValue.setClassifier(HandClassifier.STRAIGHT);
			handValue.setLevelValue(this.last().getValue());
			return handValue;
		}
		if (this.isPair()) {
			handValue.setClassifier(HandClassifier.PAIR);
			handValue.setLevelValue(this.last().getValue());

			return handValue;
		}
		if (this.isDoublePair()) {
			handValue.setClassifier(HandClassifier.TWO_PAIR);
			handValue.setLevelValue(this.last().getValue());

			return handValue;
		}
		if (this.isTrips()) {
			handValue.setClassifier(HandClassifier.TRIPS);
			handValue.setLevelValue(this.last().getValue());

			return handValue;
		}

		if (this.isFull()) {
			handValue.setClassifier(HandClassifier.FULL);
			handValue.setLevelValue(this.mainValue);
			handValue.setSecondLevel(this.secondValue);

			return handValue;
		}

		return handValue;
	}

	@Override
	public boolean hasCardValue(int level) {

		return false;
	}

	@Override
	public boolean hasAce() {
		int highest=highestValue();
		for (int i=1; i<=5;i++){
			if (highest==14){
				return true;
			}
		}
		return false;
	}

	@Override
	public int highestValue() {
		List<Card> cards = new ArrayList<>();
		cards.addAll(this);
		return this.last().getValue();
	}

	@Override
	public int compareTo(IHandResolver o) {
		return 0;
	}

}
