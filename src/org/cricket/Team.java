package org.cricket;

public class Team {
	private final String name;
	private int score;
	private int wicketsLost;
	private int ballsPlayed;
	private int superOverscore;
	private int superOverWicketsLost;
	private int superOverBallsPlayed;

	public Team(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getWicketsLost() {
		return wicketsLost;
	}

	public void setWicketsLost(int wicketsLost) {
		this.wicketsLost = wicketsLost;
	}

	public int getBallsPlayed() {
		return ballsPlayed;
	}

	public void setBallsPlayed(int ballsPlayed) {
		this.ballsPlayed = ballsPlayed;
	}

	public int getSuperOverscore() {
		return superOverscore;
	}

	public void setSuperOverscore(int superOverscore) {
		this.superOverscore = superOverscore;
	}

	public int getSuperOverWicketsLost() {
		return superOverWicketsLost;
	}

	public void setSuperOverWicketsLost(int superOverWicketsLost) {
		this.superOverWicketsLost = superOverWicketsLost;
	}

	public int getSuperOverBallsPlayed() {
		return superOverBallsPlayed;
	}

	public void setSuperOverBallsPlayed(int superOverBallsPlayed) {
		this.superOverBallsPlayed = superOverBallsPlayed;
	}

}
