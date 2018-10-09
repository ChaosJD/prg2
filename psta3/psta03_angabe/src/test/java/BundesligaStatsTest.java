import org.junit.Test;

import java.util.*;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Wolfgang Mühlbauer on 04.06.2017.
 */
public class BundesligaStatsTest {

    // alles auf Deutsch

    private static Logger logger = Logger.getLogger(BundesligaStatsTest.class.getName());

    // Verbesserung: evtl. Bundesgligaergebnisse nur einmal in Array abfragen und dann jeweils als Parameter übergeben


    @Test
    public void testAvgGoalsPerMatch() throws Exception {
        double actual = BundesligaStats.getAvgGoalsPerMatch(2015);
        logger.info("Durchschnittliche Anzahl Tore pro Spiel in Bundesligasaison 2015/2016: " + actual);
        assertEquals("Tore 1. Bundesliga 2016", 2.77, actual, 0.01);

    }

    @Test
    public void testGetTeamsOfSeason() throws Exception {
        Set<String> actual = BundesligaStats.getTeamsOfSeason(2015);
        logger.info("Teams in Bundesligasaison 2015/2016: " + actual.toString());
        assertEquals("Number of Teams", 18, actual.size());
        assertTrue("Contains Schalke 04", actual.contains("FC Schalke 04"));
    }

    @Test
    public void testGetTeamsOfAllSeasons() throws Exception {
        Integer[] years = {2011, 2012, 2013, 2014, 2015};
        List<Integer> yearList = Arrays.asList(years);
        Set<String> actual = BundesligaStats.getTeamsOfAllSeasons(yearList);
        logger.info(actual.size() + " Teams, die in allen Saisons 2011-2015 dabei waren: " + actual.toString());
        assertEquals("Number of Teams", 13, actual.size());
        assertTrue("Contains FC Augsburg", actual.contains("FC Augsburg"));
    }

    @Test
    public void testGetMatchesByTeam() throws Exception {
        Map<String, List<Match>> actual = BundesligaStats.getMatchesByTeam(2015);
        assertEquals("Number of keys", 18, actual.size());
        assertEquals("Number of matches per team", 34, actual.get("Bayern München").size());
    }

    @Test
    public void testGetDistinctResults() throws Exception {
        int actual = BundesligaStats.getDistinctResults(2015);
        logger.info("Anzahl verschiedene Spielergebnisse über ganze Saison 2015/2016: " + actual);
        assertEquals("Number of distinct results", 28, actual);
    }

    @Test
    public void testGetMatchesByNumGoals() {
        List<Match> actual = BundesligaStats.getMatchesByNumGoals(2015);
        logger.info("Maximum number of goals e.g. in match result " + actual.get(actual.size()-1).getGoalsHomeTeam() + ":" + actual.get(actual.size()-1).getGoalsGuestTeam());
        assertEquals("Maximum number of goals in a match", 8, actual.get(actual.size()-1).getGoalsHomeTeam() + actual.get(actual.size()-1).getGoalsGuestTeam());
    }

    @Test
    public void testGetMatchesByGoalDiffAndDate() {
        List<Match> actual = BundesligaStats.getMatchesByGoalDiffAndDate(2015);
        logger.info("Letztes Spiel mit höchster Tordifferenz " + actual.get(actual.size()-1).getGoalsHomeTeam() + ":" + actual.get(actual.size()-1).getGoalsGuestTeam());
        assertEquals("Letzte höchste Tordifferenz", 6, actual.get(actual.size()-1).getGoalsHomeTeam() + actual.get(actual.size()-1).getGoalsGuestTeam());
    }


    @Test
    public void testGetPointTable() {
        SortedMap<String, Integer> table = BundesligaStats.getPointTable(2015);
        logger.info("Punktestand nach Saison 2015/2016" + table.toString());
        int points = table.get("Eintracht Frankfurt");
        assertEquals("Punkte Eintracht Frankfurt", 36, points);
    }

    @Test
    public void testTeamWithMaxPoints() {
        String actual = BundesligaStats.getTeamWithMaxPoints(2015);
        logger.info("Team with maximum number of points in 2015/2016: " + actual);
        assertEquals("Team with maximum number of points in 2015/2016/", "Bayern München", actual);
    }

}