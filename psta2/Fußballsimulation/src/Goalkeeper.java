/**
 * Created by JD on 08.05.2017.
 */
public class Goalkeeper extends Person {

    private int reaction;

    public Goalkeeper(String firstName, String lastName, int kreaction)
    {
        super(firstName, lastName);
        reaction =kreaction;
    }

    public Boolean shotStoppped(int shotStrength)
    {
        if (shotStrength >= reaction)
            return false;
        return true;
    }

    public int getReaction()
    {
        return reaction;
    }


}
