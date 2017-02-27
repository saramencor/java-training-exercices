package io.robusta.birthday.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.robusta.birthday.interfaces.IGeneration;
import io.robusta.birthday.interfaces.IPeopleCollection;

/**
 * Created by Nicolas Zozol on 04/10/2016.
 */
public class Generation implements IGeneration {

	List<PeopleCollection> collections;
	

	public Generation(int n, int collectionSize) {
		this.collections = createAllRandom(n, collectionSize);
	}

	@Override
	public PeopleCollection createRandom(int size) {
		return new PeopleCollection(size);
		
	}

	@Override
	public List<PeopleCollection> createAllRandom(int n, int size) {
		// call n times createRandom(size)
		List<PeopleCollection> people = new ArrayList<>();
		for (int j = 0; j < n; j++) {
			people.add(createRandom(size));
		}
		return people;
	}

	@Override
	public List<PeopleCollection> getPeopleCollections() {

		return this.collections;
	}

	@Override
	public int getNumberOfCollectionsThatHasTwoPeopleWithSameBirthday() {
		int n = 0;

		for (int k = 0; k < collections.size(); k++) {
			if (collections.get(k).hasSame())
				n++;
		}
		return n;
	}

	@Override
	public boolean isLessThan50() {
		int nbPossitives = getNumberOfCollectionsThatHasTwoPeopleWithSameBirthday();
		if ((nbPossitives) / collections.size() < 0.5)
			return true;
		return false;
	}

}
