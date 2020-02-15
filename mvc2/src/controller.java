public class controller {
    private model model;
    private view view;
    public controller(model model, view view){
        this.model = model;
        this.view = view;
    }
    public void setGameName(String name){
        model.setName(name);
    }
    public String getGameName(){
        return model.getName();
    }
    public void setGameRating(String rollNo){
        model.setGameRating(rollNo);
    }
    public String getGameRating(){
        return model.getGameRating();
    }
    public void updateView(){
        view.printGameDetails(model.getName(), model.getGameRating());
    }
}