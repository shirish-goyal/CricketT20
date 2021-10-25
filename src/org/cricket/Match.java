package org.cricket;

import java.util.HashMap;
import java.util.Map;

public class Match {
	private Map<String, Team> teams;
	private int overs;
	private int t20TotalWickets;
	private int superOver;
	private int superOverTotalWickets;
	private boolean superOverPlayed;
	private String winner;

	public Match(String team1, String team2) {
		this.teams = new HashMap<String, Team>();
		teams.put(team1, new Team(team1));
		teams.put(team2, new Team(team2));
		this.overs = 20;
		this.t20TotalWickets = 10;
		this.superOverTotalWickets = 2;
		this.superOver = 1;
		this.superOverPlayed = false;
	}

	public boolean isSuperOverPlayed() {
		return superOverPlayed;
	}

	public void setSuperOverPlayed(boolean superOverPlayed) {
		this.superOverPlayed = superOverPlayed;
	}

	public Map<String, Team> getTeams() {
		return teams;
	}

	public int getOvers() {
		return overs;
	}

	public int getT20TotalWickets() {
		return t20TotalWickets;
	}

	public int getSuperOver() {
		return superOver;
	}

	public int getSuperOverTotalWickets() {
		return superOverTotalWickets;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
}
