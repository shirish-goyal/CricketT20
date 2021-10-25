package org.cricket;

public class Commentary {

	public void getCommentary(String ball, String bat, int scoreOnBall, String wicketOnBall) {
		if (wicketOnBall.equalsIgnoreCase("true")) {
			System.out.println("It's a wicket");
		} else {
			System.out.println("Score on " + ball + " with " + bat + " shot played : " + scoreOnBall);
		}
	}
	
	public void getCommentaryForOpening(String team) {
		System.out.println("\n"+team + " players coming in to bat.\n");
	}
}
