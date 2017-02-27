package io.robusta.birthday.implementations;

import io.robusta.birthday.interfaces.IGenerationThreshold;
import io.robusta.birthday.interfaces.IPeopleCollection;

import java.util.ArrayList;

/**
 * Created by Nicolas Zozol on 04/10/2016.
 */
public class GenerationThreshold implements IGenerationThreshold {

	public GenerationThreshold() {

	}

	@Override
	public int getSmallNumber() {
		return 0;
	}

	@Override
	public int getBigNumber() {
		return 0;
	}

	@Override
	public int findSmallestNumberOfPeopleRequiredToHave50() {
		int smallestNumber = 0;
		
		return smallestNumber;
	}

	@Override
	public float calculateProbabilityOfSame(int size) {
		float dates = 365;
		float probSum = 1;
		double totProbSum;
		float factDate = dates - size;
		float prob;

		while (dates > 0) {

			prob = (dates / factDate);
			dates--;
			factDate--;
			probSum = probSum + prob;

		}

		totProbSum = 1 - (probSum * (1 / (Math.pow(dates, size))));
		return (float) totProbSum;

	}
}
