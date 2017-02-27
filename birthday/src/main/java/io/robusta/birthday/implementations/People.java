package io.robusta.birthday.implementations;

import io.robusta.birthday.interfaces.IPeople;

/**
 * Created by Nicolas Zozol on 05/10/2016.
 */
public class People implements IPeople {

	int birthday;

	public void setBirthday(int date) {
		this.birthday = date;
	}

	public int getBirthday() {
		return this.birthday;
	}

	// should be true if two people has same birthday
	public boolean equals(IPeople people) {
		if (this.getBirthday() == people.getBirthday())
			return true;
		else

			return false;
	}
}
