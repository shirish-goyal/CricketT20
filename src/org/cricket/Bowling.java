package org.cricket;

import java.util.Random;

public class Bowling {

	private String[] bowlingcards = new String[10];

	private void SetBowlingCards() {
		bowlingcards[0] = "Bouncer";
		bowlingcards[1] = "Inswinger";
		bowlingcards[2] = "Outswinger";
		bowlingcards[3] = "Leg Cutter";
		bowlingcards[4] = "Off Cutter";
		bowlingcards[5] = "Slower Ball";
		bowlingcards[6] = "Yorker";
		bowlingcards[7] = "Pace";
		bowlingcards[8] = "Off Break";
		bowlingcards[9] = "Doosra";
	}

	public String GetBowlingCards() {
		SetBowlingCards();
		return bowlingcards[new Random().nextInt(bowlingcards.length)];
	}

}
