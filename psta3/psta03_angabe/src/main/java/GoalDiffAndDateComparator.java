import java.util.Comparator;

/**
 * Created by Wolfgang Mühlbauer on 04.06.2017.
 */
public class GoalDiffAndDateComparator implements Comparator<Match> {


    /**
     * sort by goal difference. If same goal difference then compare by Date using compareTo of LocalDateTime class
     * @param o1
     * @param o2
     * @return
     */
    public int compare(Match o1, Match o2) {
        int goalDiff1 = Math.abs(o1.getGoalsHomeTeam() - o1.getGoalsGuestTeam());
        int goalDiff2 = Math.abs(o2.getGoalsHomeTeam() - o2.getGoalsGuestTeam());

        if (goalDiff1 == goalDiff2) {
            return o1.getDatum().compareTo(o2.getDatum());
        }
        else
            return goalDiff1 - goalDiff2;


    }
}
