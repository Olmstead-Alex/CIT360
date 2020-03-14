package ApplicationControl;


// Class used to set and get the player information contained in the array.
public class Player {

    private String name;
    private String position;
    private int playerNum;

    public Player(String name, String position, int playerNum) {
        this.name = name;
        this.position = position;
        this.playerNum = playerNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    @Override
    public String toString() {
        return "Name: "+ name  + " - Position: "+ position + " - Number: "+ playerNum +" ";
    }
}