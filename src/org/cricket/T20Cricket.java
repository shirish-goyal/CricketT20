package org.cricket;

public class T20Cricket {

	final static String t1 = "IND";
	final static String t2 = "AUS";

	public static void main(String[] args) {

		Match match = new Match(t1, t2);
		Toss ts = new Toss();
		String tossResult = ts.getOutcome(t2, t1);
		System.out.println(tossResult);

		String batFirst = ts.getChose().equals("Bat") ? ts.getWinner() : ((t1).equals(ts.getWinner()) ? t2 : t1);
		String batSecond = t1.equals(batFirst) ? t2 : t1;

		int overs = match.getOvers();
		int wickets = match.getT20TotalWickets();

		Play play = new Play(match, batFirst, batSecond);
		String matchResult = play.start(wickets, overs);
		System.out.println();
		System.out.println(t1 + " v " + t2 + " : " + matchResult);

		if (match.isSuperOverPlayed()) {
			System.out.println("\nAccording to rules, " + batSecond + " and " + batFirst
					+ " will continue to bat and bowl respectively in the super over.");
			SuperOver obj = new SuperOver(match);
			String superOverResult = obj.playSuperOver(batSecond, batFirst);
			System.out.println("\nSuper Over " + t1 + " v " + t2 + " : " + superOverResult);
		}
	}
}