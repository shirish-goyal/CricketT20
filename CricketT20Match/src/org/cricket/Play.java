package org.cricket;

import java.util.concurrent.ThreadLocalRandom;

public class Play {

	private Match mt;
	private String first;
	private String second;
	protected Shot sh = new Shot();

	public Play(Match match, String first, String second) {
		this.mt = match;
		this.first = first;
		this.second = second;
	}

	public String start(int wickets, int overs) {
		Commentary cs = new Commentary();
		String batingTeam = first;
		boolean isSuperOver = mt.isSuperOverPlayed();
		for (int j = 0; j < 2; j++) {
			cs.getCommentaryForOpening(batingTeam);
			int wicket = wickets;
			int score = 0, ballsBowled = 0;
			boolean exitFlag = false;
			for (int k = 0; k < overs && wicket > 0; k++) {
				int i = 0;
				for (i = 0; i < 6 && wicket > 0; i++) {

					Bowling bb = new Bowling();
					String ball = bb.GetBowlingCards();
					Batting bw = new Batting();
					String bat = bw.GetBattingCards();
					String str = sh.GetShotTimings();
					String temp = calScore(str);
					int scoreOnBall = Integer.parseInt(temp.substring(0, temp.indexOf(',')));
					score += scoreOnBall;
					String wicketOnBall = temp.substring(temp.indexOf(',') + 1);
					wicket = "true".equalsIgnoreCase(wicketOnBall) ? wicket - 1 : wicket;
					cs.getCommentary(ball, bat, scoreOnBall, wicketOnBall);

					if (!isSuperOver) {
						mt.getTeams().get(batingTeam).setScore(score);
						mt.getTeams().get(batingTeam).setWicketsLost(wickets - wicket);
						mt.getTeams().get(batingTeam).setBallsPlayed((k * 6) + i + 1);
					} else {
						mt.getTeams().get(batingTeam).setSuperOverscore(score);
						mt.getTeams().get(batingTeam).setSuperOverWicketsLost(wickets - wicket);
						mt.getTeams().get(batingTeam).setSuperOverBallsPlayed(i + 1);
						System.out.println("Super Over : " + batingTeam + " " + score + "/" + (wickets - wicket) + " in "
								+ (i + 1) + " balls.");
					}
					if (batingTeam.equals(first)
							|| (batingTeam.equals(second) && ((!isSuperOver && mt.getTeams().get(first).getScore() >= score)
									|| (isSuperOver && mt.getTeams().get(first).getSuperOverscore() >= score)))) {
						continue;
					} else {
						exitFlag = true;
						break;
					}
				}
				if (exitFlag) {
					ballsBowled = (k * 6) + i + 1;
					break;
				} else if (wicket == 0) {
					ballsBowled = (k * 6) + i;
					break;
				} else {
					ballsBowled = (k+1) * 6;
					System.out.println("\n" + batingTeam + " is at " + score + "/" + (wickets - wicket) + " after "
							+ (k + 1) + " over(s).\n");
				}
			}
			String overBowled = (ballsBowled % 6 == 0) ? String.valueOf(ballsBowled / 6)
					: String.valueOf(ballsBowled / 6) + "." + String.valueOf(ballsBowled % 6);
			System.out.println();
			System.out.println(batingTeam + " " + score + "/" + (wickets - wicket) + " " + overBowled + " over(s)");

			batingTeam = second;
		}
		String result = "";
		if (!isSuperOver) {
			if (mt.getTeams().get(first).getScore() > mt.getTeams().get(second).getScore()) {
				result = first + " won by "
						+ (mt.getTeams().get(first).getScore() - mt.getTeams().get(second).getScore()) + " runs";
				mt.setWinner(first);
			} else if (mt.getTeams().get(first).getScore() < mt.getTeams().get(second).getScore()) {
				result = second + " won by " + (wickets - mt.getTeams().get(second).getWicketsLost()) + " wickets";
				mt.setWinner(second);
			} else {
				result = "It's a Draw. Match will now move into SuperOver";
				mt.setSuperOverPlayed(true);
			}
		} else {
			if (mt.getTeams().get(first).getSuperOverscore() > mt.getTeams().get(second).getSuperOverscore()) {
				result = first + " won in the super over.";
				mt.setWinner(first);
			} else if (mt.getTeams().get(first).getSuperOverscore() < mt.getTeams().get(second).getSuperOverscore()) {
				result = second + " won in the super over.";
				mt.setWinner(second);
			} else {
				result = "Match is a Draw.";
			}
		}

		return result;
	}

	public String calScore(String str) {
		int score = 0;
		boolean wicket = false;
		if (str.equals("Early")) {
			int val = ThreadLocalRandom.current().nextInt(-1, 3);
			if (val == -1)
				wicket = true;
			else
				score = val;
		} else if (str.equals("Good")) {
			int val = ThreadLocalRandom.current().nextInt(2, 3);
			score = val;
		} else if (str.equals("Perfect")) {
			score = ThreadLocalRandom.current().nextInt(4, 7);
		} else if (str.equals("Late")) {
			int val = ThreadLocalRandom.current().nextInt(-1, 2);
			if (val == -1)
				wicket = true;
			else
				score = val;
		}
		return score + "," + wicket;
	}
}
