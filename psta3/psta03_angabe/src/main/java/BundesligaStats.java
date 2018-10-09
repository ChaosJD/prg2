import sun.reflect.generics.tree.Tree;

import java.security.acl.Group;
import java.util.*;

/**
 * Created by Wolfgang Mühlbauer on 04.06.2017.
 */
public class BundesligaStats {


    /**
     * computes average number of goals per match for given 1.Bundesliga season
     *
     * @param year Bundesliga season, example: 2016 means Bundesliga season 2016/2017
     * @return average number of goals rounded to 2 decimal places
     */
    public static double getAvgGoalsPerMatch(int year) {

        List<Match> matches = BundesligaSeason.getAllMatchesOfSeason(year);
        int guestTeam = 0;
        int homeTeam = 0;
        int gesamt = 0;

        for (int i = 0; i < matches.size(); i++) {
            guestTeam += matches.get(i).getGoalsGuestTeam();
            homeTeam += matches.get(i).getGoalsHomeTeam();
        }
        gesamt = guestTeam + homeTeam;

        // guestTeam < homeTeam ? ((double) guestTeam/ homeTeam) : ((double) homeTeam / guestTeam
        return (double) gesamt / matches.size();
    }


    /**
     * get clubs that participated in 1.Bundesliga in given year
     *
     * @param year Bundesliga season, example: 2016 means Bundesliga season 2016/2017
     * @return set of club names represented as Strings
     */

    public static Set<String> getTeamsOfSeason(int year) {
        List<Match> matches = BundesligaSeason.getAllMatchesOfSeason(year);
        Set<String> treeSet = new TreeSet<String>();

        for (int i = 0; i < matches.size(); i++) {
            treeSet.add(matches.get(i).getGuestTeam());
            treeSet.add(matches.get(i).getHomeTeam());
        }
        return treeSet;
    }

    //Hier geht´s noch nicht.

    /**
     * get clubs that participated in 1.Bundesliga in ANY of the given years
     *
     * @param years list of years
     * @return list of club names, represented as Strings
     */
    public static Set<String> getTeamsOfAllSeasons(List<Integer> years) {
        Set<String> treeSetStringVergleich = new TreeSet<String>();

        treeSetStringVergleich.addAll(getTeamsOfSeason(years.get(0)));
        for (int i = 1; i < years.size(); i++)
            treeSetStringVergleich.retainAll(getTeamsOfSeason(years.get(i)));
        return treeSetStringVergleich;
    }


    /**
     * compute index to quickly find all matches of any team in given season
     *
     * @param year Bundesliga season, example: 2016 means Bundesliga season 2016/2017
     * @return "Map" data structure with team name as key and list of matches as value
     */
    static Map<String, List<Match>> getMatchesByTeam(int year) {
        //Gegeben, Return Liste
        Map<String, List<Match>> treemap = new TreeMap<String, List<Match>>();

        //Ergänzt
        List<Match> matches = BundesligaSeason.getAllMatchesOfSeason(year);
        List<String> arrayListString = new ArrayList<String>();

        arrayListString.addAll(getTeamsOfSeason(year));
        Iterator<String> stringArrayListIterator = arrayListString.iterator();

        Set<Match> newMatchlist = new TreeSet<Match>();
        List<Match>[] arrayMatchList = new ArrayList[arrayListString.size()];  //18Teams
        for (int i = 0; i < arrayMatchList.length; i++)
        {
            arrayMatchList[i] = new ArrayList<Match>();
        }

        boolean isDrin = false;

        int zaehler = 0;
        String s;
        while (stringArrayListIterator.hasNext()) {
            //newMatchlist.clear();;
            //isDrin = false;
            s = stringArrayListIterator.next();

            for (int i = 0; i < matches.size(); i++) {

                if ((s.equals(matches.get(i).getHomeTeam())) || (s.equals(matches.get(i).getGuestTeam()))) {
                    arrayMatchList[zaehler].add(matches.get(i)); // Was pasiert, wenn ein Wert doppelt übergeben wurde und dieser schon vorhandne ist?
                    //treemap.put(s, newMatchlist);
                    //isDrin = true;
                }
            }
            treemap.put(s, arrayMatchList[zaehler]);
/*
            if (isDrin == true) {
                arrayMatchList[zaehler].addAll(newMatchlist);
                treemap.put(s, arrayMatchList[zaehler]);
                //System.out.print("");
            }*/
            zaehler++;
        }
        return treemap;
    }


    /**
     * returns number of distinct match results in given season.
     * Example: 2:0 and 0:2 are considered as different match results
     * Mandatory: Implement equals() and hashCode() in class Match accordingly.
     *
     * @param year Bundesliga season, example: 2016 means Bundesliga season 2016/2017
     * @return number of distint match results
     */
    public static int getDistinctResults(int year) {
        //Ergänzt
        List<Match> arrayListMatchDifferent = new ArrayList<Match>();
        List<Match> matches = BundesligaSeason.getAllMatchesOfSeason(year);

       arrayListMatchDifferent.add(matches.get(0));

        boolean isDifferent;
        //treeSetMatch.addAll(matches);
        for (int i = 1; i < matches.size(); i++)
        {
            isDifferent = true;
            for (int j = 0; j < arrayListMatchDifferent.size(); j++)
            {
                if (matches.get(i).hashCode() == arrayListMatchDifferent.get(j).hashCode())
                   isDifferent = false;
            }
            if(isDifferent)
                arrayListMatchDifferent.add(matches.get(i));
        }

        return arrayListMatchDifferent.size();
    }



    /**
     * sort matches by number of goals in match in ascending order
     * Hint: Implement natural ordering, i.e. "Comparable" interface in class Match and sort with Collections
     *
     * @param year Bundesliga season, example: 2016 means Bundesliga season 2016/2017
     * @return sorted list
     */
    public static List<Match> getMatchesByNumGoals(int year) {
        List<Match> allMatchesFromSeason = BundesligaSeason.getAllMatchesOfSeason(year);

        Collections.sort(allMatchesFromSeason);

        return allMatchesFromSeason;
    }


    /**
     * sort matches by goal difference (e.g., 2:1 is smaller than 2.3).
     * If two matches have same goal difference, sort by date (which has compareTo already implemented)
     * Hint: Implement separate comparator class and sort with Collections
     *
     * @param year Bundesliga season, example: 2016 means Bundesliga season 2016/2017
     * @return sorted list
     */
    public static List<Match> getMatchesByGoalDiffAndDate(int year)
    {
        List<Match> arrayListMatch = new ArrayList<Match>();
        GoalDiffAndDateComparator goalDiffAndDateComparator = new GoalDiffAndDateComparator();
        Set<Match> treeSetMatchReturn = new TreeSet<Match>();

        arrayListMatch.addAll(getMatchesByNumGoals(year));
        Collections.reverse(arrayListMatch);

        for(int i =0; i < arrayListMatch.size(); i++)
            for(int j =0; j < arrayListMatch.size(); j++)
            {
                int variable = goalDiffAndDateComparator.compare(arrayListMatch.get(i), arrayListMatch.get(j));
                if(variable > 0)
                    treeSetMatchReturn.add(arrayListMatch.get(i));
                if(variable < 0)
                    treeSetMatchReturn.add(arrayListMatch.get(j));
            }
            arrayListMatch.addAll(treeSetMatchReturn);
        return arrayListMatch;
    }

    /**
     * compute number of total points after a complete Bundesliga season
     * Note: For each match winner team gets 3 points, loser team gets 0 points; tie / "Unentschieden": 1 points for each team
     *
     * @param year Bundesliga season, example: 2016 means Bundesliga season 2016/2017
     * @return sorted map (e.g., TreeMap) with String of team name as key and value takes the total number of points
     */
    public static SortedMap<String, Integer> getPointTable(int year)
    {
        SortedMap<String, Integer> treeMapStringInteger = new TreeMap<String, Integer>();

        List<Match> arrayListMatch = new ArrayList<Match>();
        arrayListMatch.addAll(getMatchesByNumGoals(year));


        for(int i=0; i < arrayListMatch.size(); i++)
        {
            String homeTeamName= arrayListMatch.get(i).getHomeTeam();
            String guestTeamName= arrayListMatch.get(i).getGuestTeam();
            int homeTeamPoints = arrayListMatch.get(i).getGoalsHomeTeam();
            int guestTeamPoints = arrayListMatch.get(i).getGoalsGuestTeam();

            if(homeTeamPoints > guestTeamPoints)
            {
                if(treeMapStringInteger.containsKey(homeTeamName))
                {
                    int zähler = treeMapStringInteger.get(homeTeamName);
                    treeMapStringInteger.remove(homeTeamName);
                    treeMapStringInteger.put(homeTeamName, zähler+3);
                }
                else
                    treeMapStringInteger.put(homeTeamName, 3);
            }
            else if(homeTeamPoints == guestTeamPoints)
            {
                if(treeMapStringInteger.containsKey(homeTeamName))
                {
                    int zähler = treeMapStringInteger.get(homeTeamName);
                    treeMapStringInteger.remove(homeTeamName);
                    treeMapStringInteger.put(homeTeamName, zähler+1);
                }
                else
                    treeMapStringInteger.put(homeTeamName, 1);

                if(treeMapStringInteger.containsKey(guestTeamName))
                {
                    int zähler = treeMapStringInteger.get(guestTeamName);
                    treeMapStringInteger.remove(guestTeamName);
                    treeMapStringInteger.put(homeTeamName, zähler+1);
                }
                else
                    treeMapStringInteger.put(guestTeamName, 1);
            }
            else
            {
                if(treeMapStringInteger.containsKey(guestTeamName))
                {
                    int zähler = treeMapStringInteger.get(guestTeamName);
                    treeMapStringInteger.remove(guestTeamName);
                    treeMapStringInteger.put(homeTeamName, zähler+3);
                }
                else
                    treeMapStringInteger.put(guestTeamName, 3);
            }
        }
        return treeMapStringInteger;
    }


    // treemap

    public static String getTeamWithMaxPoints(int year)
    {
        Map<String, Integer> mapHigehstTeam = getPointTable(year);
        TreeMap<Integer, String> mapNumberTeam = new TreeMap<Integer, String>(); // Warum muss hier Treemap stehen. Wieso reicht hier nicht Map aus?
        List<String> uebergangsListe = new ArrayList<String>();
        uebergangsListe.addAll(mapHigehstTeam.keySet());

        for(int i=0; i < uebergangsListe.size(); i++)
        {
           String s = uebergangsListe.get(i);
           mapNumberTeam.put(mapHigehstTeam.get(s), uebergangsListe.get(i));
        }

        String winner = mapNumberTeam.get(mapNumberTeam.lastKey());
    return winner;

    }


}
