package org.cricket;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Toss {

	private String winner;
	private String chose;

	public String getOutcome(String callingTeam, String oppositeTeam) {

		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		System.out.println(callingTeam + " Enter your choice(H/T):");
		String userCall = "";
		while (true) {
			userCall = scn.next();
			if (userCall.equalsIgnoreCase("H") || userCall.equalsIgnoreCase("T")) {
				break;
			}
			System.out.println("Enter a Valid Input");
		}
		Boolean flag = ThreadLocalRandom.current().nextBoolean();
		String n = flag ? "H" : "T";
		String choose = flag ? "Bat" : "Bowl";
		this.chose = choose;
		if (n.equalsIgnoreCase(userCall)) {
			this.winner = callingTeam;
			return callingTeam + " won the toss and choose to " + choose + " first";
		} else {
			this.winner = oppositeTeam;
			return callingTeam + " lost the toss and, " + oppositeTeam + " choose to " + choose + " first";
		}
	}

	public String getWinner() {
		return winner;
	}

	public String getChose() {
		return chose;
	}

}
