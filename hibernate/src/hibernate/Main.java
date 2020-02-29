package hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static SessionFactory factory;
    public static void main(String[] args) throws SQLException {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable x) {
            System.err.println("Failed to create sessionFactory object." + x);
            throw new ExceptionInInitializerError(x);
        }

        Main GM = new Main();

        // Add a few games records in database
        Integer gameID1 = GM.addGame("League of Legends", "Riot Games", 8);
        Integer gameID2 = GM.addGame("Fallout 4", "Bethesda", 10);
        Integer gameID3 = GM.addGame("The Witcher 3: Wild Hunt", "CD Projekt Red", 10);

        //list all games
        GM.listGames();

        // update a game
        GM.updateGame(gameID1, 9);

        // delete a game
        GM.deleteGame(gameID2);

        // show new list
        GM.listGames();
    }

    // method to CREATE a new game
    public Integer addGame(String gameName, String developer, int rating){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer gameID = null;

        try {
            tx = session.beginTransaction();
            Game game = new Game(gameName, developer, rating);
            gameID = (Integer) session.save(game);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return gameID;
    }

    // method to read games
    public void listGames( ) throws SQLException {
        Connection tx = null;
        try (Session session = factory.openSession()) {
            tx = (Connection) session.beginTransaction();
            List games = session.createQuery("FROM Game").list();
            for (Object game1 : games) {
                Game game = (Game) game1;
                System.out.print("Name: " + game.getGameName());
                System.out.print("  Developer: " + game.getDeveloper());
                System.out.println("  Rating: " + game.getRating());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // method to update 
    public void updateGame(Integer gameID, int rating ) throws SQLException {
        Connection cx = null;
        try (Session session = factory.openSession()) {
            Transaction tx = null;
            tx = session.beginTransaction();
            Game game = session.get(Game.class, gameID);
            game.setRating(rating);
            session.update(game);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    // method to delete
    public void deleteGame(Integer gameID) throws SQLException {
        Connection cx = null;
        try (Session session = factory.openSession()) {
            Transaction tx = null;
            tx = session.beginTransaction();
            Game employee = (Game) session.get(Game.class, gameID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
