public class mvc2 {
    public static void main(String[] args) {
        //get game from database
        model model  = retrieveGameFromDatabase();
        //Show game details
        view view = new view();
        controller controller = new controller(model, view);
        controller.updateView();
        //update
        controller.setGameName("League of Legends");
        controller.setGameRating("T");
        controller.updateView();
    }
    private static model retrieveGameFromDatabase(){
        model game = new model();
        game.setName("XCOM 2");
        game.setGameRating("M");
        return game;
    }
}