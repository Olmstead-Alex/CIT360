import java.util.*;
import java.util.Scanner;

public class collection {

    public static void main(String[] args) {
        //Create the HashSet
        HashSet<String> Games = new HashSet<>();

        //Add games to the HashSet
        Games.add("Assassin's Creed");
        Games.add("Halo: Combat Evolved");
        Games.add("Fallout 4");
        Games.add("The Witcher 3: Wild Hunt");
        Games.add("XCOM 2");

        System.out.println(Games);

        //Check if game exists, if not, add game to list
        boolean found = Games.contains("League of Legends");
        System.out.println(found +"\n");
        if (!found) {
            Games.add("League of Legends");
            System.out.println("Added new game! New list is:");
            System.out.println(Games +"\n");
        }

        //Remove a game
        System.out.println("Actually, lets remove that game we just added.");
        Games.remove("League of Legends");
        System.out.println("League of Legends removed! New list is:");
        System.out.println(Games +"\n");

        //Iterate over games
        System.out.println("Now, lets interate through the array and make a nice looking list.");
        Iterator<String> itr = Games.iterator();
        while(itr.hasNext())
        {
            String game = itr.next();
            System.out.println("Game: " + game);
        }


        //Let user add something to the list
        Scanner input = new Scanner(System.in);
        System.out.print("\nHey! How about you add something?");
        System.out.print("\nEnter your favorite game: ");
        String newgame = input.nextLine();
        Games.add(newgame);
        System.out.println(Games);

        //Alphabetize games, ultimately in a nice looking list like the one above
        System.out.println("\nI think it would look better alphabetized. Let's do that.");
        List<String> sort = new ArrayList<String>(Games);
        Collections.sort(sort);
        System.out.println(sort);

        System.out.println("\nOkay. That's a step in the right direction. Now, let's make it look nice.");
        Iterator<String> itr2 = sort.iterator();
        while (itr2.hasNext())
        {
            String game = itr2.next();
            System.out.println("Game: " + game);
        }

        }

    }

