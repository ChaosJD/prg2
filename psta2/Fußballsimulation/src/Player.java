/**
 * Created by JD on 08.05.2017.
 */
public class Player extends Person
{

    private int strength;
    private int scoredGoals;

    public Player(String firstName, String lastName, int kstrength)
    {
        super (firstName, lastName);
        scoredGoals = 0;
        strength = kstrength;
    }

    public int shoot()
    {
        int strengtV = strength;
        strengtV = (int)((double) strength * Math.random());
        return strengtV;
    }

    public int getStrength()
    {
        return strength;
    }

    public int getScoredGoals()
    {
        return scoredGoals;
    }


    public void setScoredGoals(int mScoredGoals)
    {
        scoredGoals += mScoredGoals;
    }

}
