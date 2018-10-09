import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Created by Wolfgang Mühlbauer on 24.05.2017.
 */
public class Match implements Comparable<Match> {

    // DO NOT MODIFY EXISTING CODE; ONLY ADD CODE IF NECESSARY


    // GETTER METHODES
    public LocalDateTime getDatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(datum, formatter);
        return date;
    }

    public int getSpieltag()
    {
        return group.groupOrderID;
    }

    public String getHomeTeam() {return heimMannschaft.teamName;}
    public String getGuestTeam() {return gastMannschaft.teamName;}
    public int getGoalsHomeTeam() {return matchResults[1].toreTeam1; }
    public int getGoalsGuestTeam() {return matchResults[1].toreTeam2; }



    @SerializedName("MatchDateTime")
    @Expose
    private String datum;

   // public Group getGroup() {
     //   return group;
    //}

    @SerializedName("Group")
    @Expose
    private Group group;

    @SerializedName("Team1")
    @Expose
    private Team heimMannschaft;

    @SerializedName("Team2")
    @Expose
    private Team gastMannschaft;

    @SerializedName("MatchResults")
    @Expose
    private MatchResult[] matchResults = new MatchResult[2];

    // inner classes
    private class Group
    {
        public int getGroupOrderID() {
            return groupOrderID;
        }

        @SerializedName("GroupOrderID")
        @Expose
        private int groupOrderID;
    }

    private class Team {
        @SerializedName("TeamName")
        @Expose
        private String teamName;
    }

    // nicht ganz sicher, dass letzter Array-Eintrag der Endstand ist.
    private class MatchResult {
        @SerializedName("PointsTeam1")
        @Expose
        private int toreTeam1;

        @SerializedName("PointsTeam2")
        @Expose
        private int toreTeam2;
    }

    @Override
    /**
     * Two objects are assumed to be equal if the match result is the same
     * Note: 2:0 and 0:2 represent different match results
     * @param o object to compare with
     * @return true if objects represent the same match result
     */
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (this.getClass() != o.getClass())
            return false;
        Match other = (Match) o;

        if (this.getGoalsHomeTeam() == other.getGoalsHomeTeam() || this.getGoalsGuestTeam() == other.getGoalsHomeTeam())
            return true;
        else
            return false;
    }

    @Override
    /**
     * Two objects shall return same hashCode if their match result is the same
     * Note: 2:0 and 0:2 shall return different match results
     * @return hash code
     */
    public int hashCode() {
        int result = getGoalsHomeTeam() * 31 + getGoalsGuestTeam();
        return result;
    }


    /**
     * optional: toString method outputs the match result
     * @return match result e.g., 2:0
     */
    @Override
    public String toString() {
        return "Match{" +
                getGoalsHomeTeam() + ":" + getGoalsGuestTeam() +
                '}';
    }

    /**
     * implements Comparable interface. Sort matches by total number of goals scored in a match.
     * Example: match1 (3:2) has 5 goals und match2 (1:2) has 3 goals -> match1.compareTo(match2) shall return a positive value
     * @param o match to compare with
     * @return positive value if more goals in current match than in other match
     */
    public int compareTo(Match o) {
        int thisMatchGoals = getGoalsHomeTeam() + getGoalsGuestTeam();
        int otherMatchGoals = o.getGoalsHomeTeam() + getGoalsGuestTeam();
        return thisMatchGoals - otherMatchGoals;
    }

}


