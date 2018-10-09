/**
 * Created by JD on 08.05.2017.
 */
public class Trainer extends Person
{
    private int strategy;

    public Trainer(String firstName, String lastName, int kstrategy)
    {
        super(firstName, lastName);
        strategy = kstrategy ;
    }


    public int getStrategy()
    {
        return strategy;
    }

}
