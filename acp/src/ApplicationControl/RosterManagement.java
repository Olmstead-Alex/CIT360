package ApplicationControl;

import java.util.HashMap;
import java.util.Scanner;

import ApplicationControl.Library;
/*
Application Control Pattern is similar to MVC in that it makes code more modular.  You can use handlers to take care of
different instances that you have in the program.  In my program I have an instance to show the players that have been
added to a list array collection in the Library.java file.  The second instance allows the user to add to the list array.
After the selection is made the show menu handler is used again to display the menu to the screen.
Code is based on tutorial by Brad Lawrence at https://www.youtube.com/watch?v=KCwfbL6NkpA
and https://github.com/Lawrence-Brad/ApplicationControllerExample/tree/master/src/main
I wrote and edited code to suit my program.
*/



public class RosterManagement {

    public RosterManagement() {
    }

    // Program is run from this class.  It will delegate to ApplicationControl.java to carry out whatever option is
    // selected.
    public static void main(String[] args) {

        ApplicationControl controller = new ApplicationControl();
        Scanner scanner = new Scanner(System.in);
        Library playerLib = new Library();

        try {

            int action = -1;
            while (action != 0) {
                action = (int)controller.doCommand("showMenu", GetCommandData("scanner", scanner));
                switch (action) {
                    case 1:
                        controller.doCommand("showPlayer", GetCommandData("library", playerLib));
                        break;
                    case 2:
                        HashMap<String, Object> commandData = GetCommandData("scanner", scanner);
                        commandData.put("library", playerLib);
                        controller.doCommand("addPlayer", commandData);
                        break;
                    case 0:
                        System.out.println("Thank you for using the System.");
                        break;
                    default:
                        System.out.println("Selection not available.  Please try again.");
                        break;

                }

            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
        finally {
            scanner.close();
        }
    }

    public static HashMap<String, Object> GetCommandData(String key, Object value) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(key, value);
        return data;
    }
}