package org.cricket;

import java.util.Random;

public class Shot {

	private String[] shotTimings = new String[4];

	private void SetShotTimings() {
		shotTimings[0] = "Early";
		shotTimings[1] = "Good";
		shotTimings[2] = "Perfect";
		shotTimings[3] = "Late";
	}

	public String GetShotTimings() {
		SetShotTimings();
		return shotTimings[new Random().nextInt(shotTimings.length)];
	}
}
