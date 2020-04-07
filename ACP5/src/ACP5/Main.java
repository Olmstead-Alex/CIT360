package ACP5;

import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
    {
    //Prompt user to pull up a page;
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease type 1, 2, or 3 to pull up one of the pages:");
        Controller app = new Controller();
        app.dispatchRequest(input.next());

    }
}
