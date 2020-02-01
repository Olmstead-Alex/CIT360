import java.net.*;
import java.io.*;
import java.security.Permission;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
                System.out.println("Connection Failed.\n");
            }
            if (response.equals("OK")) {
                System.out.println("Connection Successful.\n");
            }

            // is the URL using a proxy?
            boolean proxy = connection.usingProxy();
            if (!proxy) {
                System.out.println("This URL is not using a proxy connection.\n");
            }
            if (proxy) {
                System.out.println("This URL is using a proxy connection.\n");
            }
            
            //Permission query
            Permission request = connection.getPermission();
            System.out.println("This is the Permission used: " + request + "\n");

            //Pausing for a few seconds, so the user can read above output.
            TimeUnit.SECONDS.sleep(5);


            //Output html from specified website.
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String LineInput;

            while ((LineInput = in.readLine()) != null)
                System.out.println(LineInput);
            in.close();


            //end of code. Disconnect from URL
            connection.disconnect();

        }   //catch will catch errors, and run a stake trace for us
            catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
