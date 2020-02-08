//import jackson and exception handler

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


//create class and get things going
public class json {

    private static Object Game;

    //Convert object to JSON and save output
    public static void Object_JSON(Game newGame) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Game game = exampleGame();

        try {
            //create file and write value to it
            mapper.writeValue(new File("C:\\Users\\alexo\\IdeaProjects\\json\\src\\game.json"), game);

            String jsonInString = mapper.writeValueAsString(game);
            System.out.println(jsonInString);

            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(game);
            System.out.println(jsonInString);

            //catch errors
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Now JSON to Object
    public static void JSON_Object(Game newGame) throws Exception {

        ObjectMapper mapper = new ObjectMapper();


        try {

            // Convert JSON string from file to Object
            Game game = mapper.readValue(new File("C:\\Users\\alexo\\IdeaProjects\\json\\src\\game.json"), Game.class);
            System.out.println(game);

            // Convert JSON string to Object
            String JSONstring = "{\n" +
                    "  \"name\" : \"League of Legends\",\n" +
                    "  \"year\" : 2008,\n" +
                    "  \"publisher\" : \"Riot Games\",\n" +
                    "  \"rating\" : \"T\"\n" +
                    "}";

            Game game2 = mapper.readValue(JSONstring, Game.class);

            //pretty print
            String prettyGame = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(game2);
            System.out.println(prettyGame);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Game exampleGame(){
            Game game = new Game();
            game.setName("League of Legends");
            game.setPublisher("Riot Games");
            game.setRating("T");
            game.setYear(2008);
        return game;
    }

    public static void main(String[] args) throws Exception {

        //Greet user
        System.out.println("Hello! This program should effectively demonstrate that I can convert an object to JSON and convert JSON to an object in Java." +
                "\nHere we go!\n");

        System.out.println("First we will convert an object I created to JSON and output that info below:\n");
        //Object to JSON
        Object_JSON(exampleGame());
        System.out.println("\nNext we will convert that JSON back to a java object and output that info below:\n");
        //JSON to Object
        JSON_Object(exampleGame());

    }
}
