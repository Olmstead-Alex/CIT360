package ApplicationControl;

import java.util.ArrayList;

// Library of players  used to display the roster add players to.
public class Library {

    private ArrayList<Player> players;

    public Library() {
        players = new ArrayList<>();
        players.add(new Player("Carson Wentz", "Quarterback", 11));
        players.add(new Player("Miles Sanders", "Running Back", 26));
        players.add(new Player("Fletcher Cox", "Defensive Tackle", 91));
        players.add(new Player("DeSean Jackson", "Wide Receiver", 10));

    }

    public void addPlayers(Player player) {
        this.players.add(player);

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Library [players=\n\t");
        ArrayList<String> playerList = new ArrayList<>();
        for (Player player : players) {
            playerList.add(player.toString());
        }
        String playerStrings = String.join(",\n\t", playerList);
        builder.append(playerStrings);
        builder.append("\n");
        return builder.toString();
    }
}

