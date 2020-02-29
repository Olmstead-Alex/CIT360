package hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Games")
public class Game {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "game")
    private String gameName;
    @Column(name = "developer")
    private String developer;
    @Column(name = "rating")
    private Integer rating;
    public Game() {

    }
    public Game(String gameName, String developer, Integer rating) {
        this.gameName = gameName;
        this.developer = developer;
        this.rating = rating;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override

    public String toString() {
        return "Game [id=" + id + ", gameName = " + gameName + ", developer = " + developer + ", rating = " + rating + "]";
    }

}
