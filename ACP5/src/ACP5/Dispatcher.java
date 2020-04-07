package ACP5;

public class Dispatcher
{
    private Game1 one;
    private Game2 two;
    private Game3 three;

    public Dispatcher()
    {
        one = new Game1();
        two = new Game2();
        three = new Game3();
    }

    public void dispatch( String request )
    {
        if( request.equalsIgnoreCase("1") )
        {
            one.show();
        }
        else if( request.equalsIgnoreCase("2") )
        {
            two.show();
        }
        else if( request.equalsIgnoreCase("3") )
        {
            three.show();
        }
        else
        {
            System.err.println("Please make a valid request.");
        }
    }
}
