/**
 * Created by JD on 08.05.2017.
 */
public class Team
{
    private Trainer trainer;
    private Goalkeeper goalkeeper;
    private Player[] players = new Player[10];

    private String name;

    public Team(Trainer ktrainer, Goalkeeper kgoalkeeper, Player[] kplayers,String kname)
    {
        trainer = ktrainer;
        goalkeeper = kgoalkeeper;
        players = kplayers;
        name = kname;
    }

    public int getStrength()
    {
        int strength = 0;
        for(int i = 1; i < players.length; i++)
        {
          strength += players[i].getStrength();
        }
        //return strength;

        strength += goalkeeper.getReaction();
        strength += trainer.getStrategy();
        return  strength;
    }

    public String getName()
    {
        return name;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    public Goalkeeper getGoalkeeper()
    {
        return goalkeeper;
    }
}
