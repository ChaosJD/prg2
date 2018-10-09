/**
 * Created by JD on 08.05.2017.
 */
public class Match
{
    private Team[] team = new Team[2];  //Varibalen oder verzweigung
    private final int SPIELMINUTEN = 90;
    private final int ACTION = 5;  //hier: Jede 5. Spielminute

    public Match(Team kteam1, Team kteam2)
    {
        team[0] = kteam1;
        team[1] = kteam2;
    }

    public void play() // nochmal code Ürberpüfen.
    {
        int zaehlerGesamtA = 0;
        int zaehlerGesamtB = 0;

          // Für spieler
        for( int i = 0; i < SPIELMINUTEN +1; i+=ACTION)
        {
            int zaehlerA = 0;
            int zaehlerB = 0;

            int zufallszahl =  (int) (Math.random() * 10);
            int offensive = whoStarts();
            int defensive = defence(offensive);
            //int zufallszahl =(int)Math.random()*10;
            if( i==0)
                System.out.println(i+1 + ". Minute");
            else
                System.out.println(i + ". Minute");

            System.out.println("Chance für: " + team[offensive].getPlayers()[zufallszahl].getNachname() + "...");
            System.out.println(team[offensive].getPlayers()[zufallszahl].getNachname() +" "+ "Zieht ab ");

            if(team[defensive].getGoalkeeper().shotStoppped(team[offensive].getPlayers()[zufallszahl].shoot()))
            {
                team[offensive].getPlayers()[zufallszahl].setScoredGoals(1);
                System.out.print("Toor!!! ");

                for(int j = 0; j < 10; j++)
                {
                    zaehlerA += team[0].getPlayers()[j].getScoredGoals();
                }

                for(int j =0; j < 10; j++)
                {
                    zaehlerB += team[1].getPlayers()[j].getScoredGoals();
                }
                zaehlerGesamtA = zaehlerA;
                zaehlerGesamtB = zaehlerB;
                System.out.print(zaehlerA + ":" + zaehlerB + " " + team[offensive].getPlayers()[zufallszahl].getNachname()+ "\n" + "\n" );
            }
            else
            {
                System.out.println(team[defensive].getGoalkeeper().getNachname() + " pariert glanzvoll" + "\n");
            }


        }
        System.out.println("-----------------------------------------------");
        System.out.println(" Das Freundschaftsspiel endete\n");
        System.out.println(team[0].getName() + " - " + team[1].getName() + " " + zaehlerGesamtA + " : " + zaehlerGesamtB +".");
        System.out.println("------------------------------------------------");

    }

    public int whoStarts()
    {
        double zufall = Math.random();
        int staerkeTeamA = (int) ((double)team[0].getStrength()*zufall);
        int staerkeTeamB= (int) ((double)team[1].getStrength()*zufall);

        if (staerkeTeamA < staerkeTeamB)
            return 1;
        return 0;
    }

    public int defence(int offensive)
    {
        if(offensive == 0)
            return 1;
            return 0;
    }
}
