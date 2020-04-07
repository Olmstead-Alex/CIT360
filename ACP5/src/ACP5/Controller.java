package ACP5;

public class Controller
{
    private Dispatcher dispatcher;

    public Controller()
    {
        dispatcher = new Dispatcher();
    }

    private boolean isAuthenticUser()
    {
        //maybe write some authentication stuff... otherwise:
        System.out.println("User is authenticated successfully.");
        return true;
    }

    public void dispatchRequest( String request )
    {

        // is user authenticated?
        if( isAuthenticUser() )
        {
            dispatcher.dispatch(request);
        }
    }
}
