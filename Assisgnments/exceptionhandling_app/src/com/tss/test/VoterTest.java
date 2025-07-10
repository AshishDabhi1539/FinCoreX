package com.tss.test;
import com.tss.ExceptionClass.AgeNotValidException;
import com.tss.model.Voter;

public class VoterTest {
	public static void main(String []args) throws AgeNotValidException {
		Voter voter = new Voter("Harshad",210,19);
		voter.toString();
		
		Voter voter2 = new Voter("Mahek",310,10);
		voter.toString();
	}
}