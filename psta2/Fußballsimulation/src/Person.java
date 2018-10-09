/**
 * Created by JD on 08.05.2017.
 */
public class Person
{
    private String vorname;
    private String nachname;

    public Person(String firstName, String lastName)
    {
        vorname = firstName;
        nachname= lastName;
    }

    public String getVorname()
    {
        return vorname;
    }

    public String getNachname()
    {
        return nachname;
    }
}
