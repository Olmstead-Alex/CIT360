package ApplicationControl;

import java.util.HashMap;
import java.util.Scanner;

// Handler used when adding a player to the Library.
class AddPlayerHandler implements Handler {

    @Override
    public Object handleIt(HashMap<String, Object> data) {
        Scanner scanner = (Scanner) data.get("scanner");
        Library playerLib = (Library) data.get("library");

        System.out.println("Enter player name: ");
        String name = scanner.nextLine();
        System.out.println("Enter players position: ");
        String position = scanner.nextLine();
        System.out.println("Enter the players number");
        int playerNum = scanner.nextInt();

        Player newPlayer = new Player(name, position, playerNum);
        playerLib.addPlayers(newPlayer);
        return null;

    }
}
public class ApplicationControl {

    private HashMap<String, AddPlayerHandler> commands;

    public ApplicationControl() {
        commands = new HashMap<String, AddPlayerHandler>();
        final AddPlayerHandler addPlayer;
        addPlayer = commands.put("addPlayer", new AddPlayerHandler());
        //commands.put("showPlayer", new ShowPlayerHandler());
        //commands.put("showMenu", new ShowMenuHandler());
    }

    public Object doCommand(String commandKey, HashMap<String, Object> commandData) throws Exception {
        AddPlayerHandler command = commands.getOrDefault(commandKey, null);
        if (command == null) {
            throw new Exception("There is no command " + commandKey + " ");
        }

        return command.handleIt(commandData);
    }


}