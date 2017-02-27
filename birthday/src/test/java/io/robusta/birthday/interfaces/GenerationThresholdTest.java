package io.robusta.birthday.interfaces;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.robusta.birthday.implementations.Generation;
import io.robusta.birthday.implementations.GenerationThreshold;
import io.robusta.birthday.implementations.PeopleCollection;

public class GenerationThresholdTest {
	
	GenerationThreshold generationThreshold = new GenerationThreshold();

	@Test
	 public void findSmallestNumberOfPeopleRequiredToHave50() throws Exception {
		
		assertTrue(generationThreshold.findSmallestNumberOfPeopleRequiredToHave50() == 23);
	}
	@Test
	public void calculateProbabilityOfSame() throws Exception{

		assertTrue(generationThreshold.calculateProbabilityOfSame(3)<0.5);
		
	
		assertTrue(generationThreshold.calculateProbabilityOfSame(0) == 0);
		
	
		assertTrue(generationThreshold.calculateProbabilityOfSame(365) == 1);
	}

				
	}


