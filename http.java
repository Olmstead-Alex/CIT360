import java.net.*;
import java.io.*;
import java.util.*;

public class http {

    public static void main(String[] args) throws IOException {

        //Get user input for website
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter website you wish to connect to (All you need to do is say 'google.com'): ");
        String userHTTP = userInput.nextLine();

        try {
            URL url = new URL("http://" + userHTTP);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);

            //Get response from URL
            String response = connection.getResponseMessage();

            // if statements to inform user if connection was successful or not.
            if (response.equals("Not Found")) {
                System.out.println("Connection Failed.");
            }
            if (response.equals("OK")) {
                System.out.println("Connection Successful.");
            }

            //Output html from specified website.
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String LineInput;

            while ((LineInput = in.readLine()) != null)
                System.out.println(LineInput);
            in.close();


            //end of code. Disconnect from URL
            connection.disconnect();

        }   //catch will catch errors, and run a stake trace for us
            catch (IOException e) {
            e.printStackTrace();
        }
    }
}
