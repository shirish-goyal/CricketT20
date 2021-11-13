package org.cricket;

import java.util.concurrent.ThreadLocalRandom;

public class Play {

	private Match objMatch;
	private String inningFirst;
	private String innningSecond;
	protected Shot objShotTim = new Shot();

	public Play(Match match, String inningFirst, String innningSecond) {
		this.objMatch = match;
		this.inningFirst = inningFirst;
		this.innningSecond = innningSecond;
	}

	public String start(int totalWickets, int totalOvers) {
		Commentary cs = new Commentary();
		String batingTeam = inningFirst;
		boolean isSuperOver = objMatch.isSuperOverPlayed();
		for (int j = 0; j < 2; j++) {
			cs.getCommentaryForOpening(batingTeam);
			int wicketRemain = totalWickets;
			int score = 0, ballsBowled = 0;
			boolean exitFlag = false;
			for (int k = 0; k < totalOvers && wicketRemain > 0; k++) {
				int i = 0;
				for (i = 0; i < 6 && wicketRemain > 0; i++) {

					Bowling objBowl = new Bowling();
					String ball = objBowl.GetBowlingCards();
					Batting objBat = new Batting();
					String bat = objBat.GetBattingCards();
					String timing = objShotTim.GetShotTimings();
					String outComeOnBall = calScore(timing);
					int scoreOnBall = Integer.parseInt(outComeOnBall.substring(0, outComeOnBall.indexOf(',')));
					score += scoreOnBall;
					String wicketOnBall = outComeOnBall.substring(outComeOnBall.indexOf(',') + 1);
					wicketRemain = "true".equalsIgnoreCase(wicketOnBall) ? wicketRemain - 1 : wicketRemain;
					cs.getCommentary(ball, bat, scoreOnBall, wicketOnBall);

					if (!isSuperOver) {
						objMatch.getTeams().get(batingTeam).setScore(score);
						objMatch.getTeams().get(batingTeam).setWicketsLost(totalWickets - wicketRemain);
						objMatch.getTeams().get(batingTeam).setBallsPlayed((k * 6) + i + 1);
					} else {
						objMatch.getTeams().get(batingTeam).setSuperOverscore(score);
						objMatch.getTeams().get(batingTeam).setSuperOverWicketsLost(totalWickets - wicketRemain);
						objMatch.getTeams().get(batingTeam).setSuperOverBallsPlayed(i + 1);
						System.out.println("Super Over : " + batingTeam + " " + score + "/" + (totalWickets - wicketRemain)
								+ " in " + (i + 1) + " balls.");
					}
					if (batingTeam.equals(inningFirst) || (batingTeam.equals(innningSecond) && ((!isSuperOver
							&& objMatch.getTeams().get(inningFirst).getScore() >= score)
							|| (isSuperOver && objMatch.getTeams().get(inningFirst).getSuperOverscore() >= score)))) {
						continue;
					} else {
						exitFlag = true;
						break;
					}
				}
				if (exitFlag) {
					ballsBowled = (k * 6) + i + 1;
					break;
				} else if (wicketRemain == 0) {
					ballsBowled = (k * 6) + i;
					break;
				} else {
					ballsBowled = (k + 1) * 6;
					System.out.println("\n" + batingTeam + " is at " + score + "/" + (totalWickets - wicketRemain) + " after "
							+ (k + 1) + " over(s).\n");
				}
			}
			String overBowled = oversBowledInAnInning(ballsBowled);

			System.out.println(
					"\n" + batingTeam + " " + score + "/" + (totalWickets - wicketRemain) + " " + overBowled + " over(s)");

			batingTeam = innningSecond;
		}
		String result = getOutcomeAfterBothInnings(isSuperOver, totalOvers);
		return result;
	}

	public String oversBowledInAnInning(int ballsBowled) {
		String overBowled;
		try {
			overBowled = (ballsBowled % 6 == 0) ? String.valueOf(ballsBowled / 6)
					: String.valueOf(ballsBowled / 6) + "." + String.valueOf(ballsBowled % 6);
		} catch (Exception e) {
			overBowled = "0";
			e.printStackTrace();
		}
		return overBowled;
	}

	public String calScore(String timing) {
		int score = 0;
		boolean wicket = false;
		if (timing.equals("Early")) {
			int val = ThreadLocalRandom.current().nextInt(-1, 3);
			if (val == -1)
				wicket = true;
			else
				score = val;
		} else if (timing.equals("Good")) {
			int val = ThreadLocalRandom.current().nextInt(2, 3);
			score = val;
		} else if (timing.equals("Perfect")) {
			score = ThreadLocalRandom.current().nextInt(4, 7);
		} else if (timing.equals("Late")) {
			int val = ThreadLocalRandom.current().nextInt(-1, 2);
			if (val == -1)
				wicket = true;
			else
				score = val;
		}
		return score + "," + wicket;
	}

	public String getOutcomeAfterBothInnings(boolean isSuperOver, int totalOvers) {
		String result = "";
		if (!isSuperOver) {
			if (objMatch.getTeams().get(inningFirst).getScore() > objMatch.getTeams().get(innningSecond).getScore()) {
				result = inningFirst + " won by " + (objMatch.getTeams().get(inningFirst).getScore()
						- objMatch.getTeams().get(innningSecond).getScore()) + " runs";
				objMatch.setWinner(inningFirst);
			} else if (objMatch.getTeams().get(inningFirst).getScore() < objMatch.getTeams().get(innningSecond)
					.getScore()) {
				result = innningSecond + " won by "
						+ (totalOvers - objMatch.getTeams().get(innningSecond).getWicketsLost()) + " wickets";
				objMatch.setWinner(innningSecond);
			} else {
				result = "It's a Draw. Match will now move into SuperOver";
				objMatch.setSuperOverPlayed(true);
			}
		} else {
			if (objMatch.getTeams().get(inningFirst).getSuperOverscore() > objMatch.getTeams().get(innningSecond)
					.getSuperOverscore()) {
				result = inningFirst + " won in the super over.";
				objMatch.setWinner(inningFirst);
			} else if (objMatch.getTeams().get(inningFirst).getSuperOverscore() < objMatch.getTeams().get(innningSecond)
					.getSuperOverscore()) {
				result = innningSecond + " won in the super over.";
				objMatch.setWinner(innningSecond);
			} else {
				result = "Match is a Draw.";
			}
		}
		return result;
	}
}
