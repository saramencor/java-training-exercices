package io.robusta.hand;

public enum HandClassifier {

	HIGH_CARD, PAIR, TWO_PAIR, TRIPS, STRAIGHT, 
	FLUSH, FULL, FOUR_OF_A_KIND, STRAIGHT_FLUSH;
	//flush couleur, straigth flush escalera de color
	
	public int getValue(){
		return this.ordinal();
	}
	
}
