/**
 * Created by JD on 08.05.2017.
 */
public class Test
{
    public static void main(String[] args)
    {
        Player[] mannschaftA = new Player[10];
        Player[] mannschaftB = new Player[10];

        //Player FC Bayern -> MannschaftA
        mannschaftA[0] = new Player("David", "Alaba",9);
        mannschaftA[1] = new Player("Jerome", "Boateng",8);
        mannschaftA[2] = new Player("Mats", "Hummels",7);
        mannschaftA[3] = new Player("Pillip", "Lahm",9);
        mannschaftA[4] = new Player("Frank", "Ribery",8);
        mannschaftA[5] = new Player("Arturo", "Vidal",7);
        mannschaftA[6] = new Player("Erdal", "Öztürk",9);
        mannschaftA[7] = new Player("Robert", "Lewandowski",8);
        mannschaftA[8] = new Player("Thomas", "Müller",7);
        mannschaftA[9] = new Player("Arjen", "Robben",9);

        //Player BVB -> MannschaftB
        mannschaftB[0] =  new Player("Erik", "Durm", 9);
        mannschaftB[1] =  new Player("Matthias", "Ginter",8);
        mannschaftB[2] =  new Player("Lukas", "Piszczek", 7);
        mannschaftB[3] =  new Player("Marcel", "Schmelzer", 9);
        mannschaftB[4] =  new Player("Sven", "Bender",8);
        mannschaftB[5] =  new Player("Gonzalo", "Castro",7);
        mannschaftB[6] =  new Player("Mario", "Götze",9);
        mannschaftB[7] =  new Player("Alexander", "Isak",8);
        mannschaftB[8] =  new Player("Marcel", "Reus",7);
        mannschaftB[9] = new Player ("Andre", "Schürle",9);

        //Torwärter
        Goalkeeper[] g = new Goalkeeper[2];
        g[0] = new Goalkeeper("Manuel", "Neuer", 7);
        g[1] = new Goalkeeper("Roman", "Weidenfeller", 8);

        //Trainer
        Trainer[] t = new Trainer[2];
        t[0] = new Trainer("Carlo", "Angelotti", 5);
        t[1] = new Trainer("Thomas", "Tuchel",5);

        //Teams
        Team bayern = new Team(t[0], g[0], mannschaftA, "FC Bayern");
        Team bvb = new Team(t[1], g[1], mannschaftB, "Borussia Dortmund");

        //Match
        Match match = new Match(bayern, bvb);

        System.out.println("Start des Freundschaftsspiels zwischen");
        System.out.println(bayern.getName());
        System.out.println("Trainer: " + t[0].getNachname());
        System.out.println("und");
        System.out.println(bvb.getName());
        System.out.println("Trainer: " + t[1].getNachname());
        System.out.println("----------------------------------------------");

        match.play();



        //Fragen. Gibt es noch andere Klassen von denen man Ableitungen kann, die nicht public sind.bei final gehts nicht.
        // Final wäre möglich, wir aber in der realtität gemacht. Nicht sinnvoll.
        //Frage. Sind die Trainer, Player und Goalkeeper weitgehnds final zu gestallten. d.h. endgültige Unterklassen alle final?
        //Farge. strength, strategy, reaction indirekte anweisung für zufallszahlen. Können auch fix vergeben werden.
        //Frage. GetStrength von Team ist die Addition der Strength. Stimmt
    }
}
