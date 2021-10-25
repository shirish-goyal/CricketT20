package org.cricket;

public class SuperOver {

	private Match match;
	
	public SuperOver(Match match) {
		this.match = match;
	}
	
	public String playSuperOver(String first, String second) {

		int overs = match.getSuperOver();
		int wickets = match.getSuperOverTotalWickets();
		
		Play play = new Play(match, first, second);
		String outcome = play.start(wickets, overs);
		return outcome;
		
	}
}
