package org.cricket;

import java.util.Random;

public class Batting {

	private String[] battingcards = new String[10];

	private void SetBattingCards() {

		battingcards[0] = "Straight";
		battingcards[1] = "Sweep";
		battingcards[2] = "Flick";
		battingcards[3] = "CoverDrive";
		battingcards[4] = "LegLance";
		battingcards[5] = "Pull";
		battingcards[6] = "Long On";
		battingcards[7] = "Scoop";
		battingcards[8] = "SquareCut";
		battingcards[9] = "UpperCut";
	}

	public String GetBattingCards() {
		SetBattingCards();
		return battingcards[new Random().nextInt(battingcards.length)];
	}

}
