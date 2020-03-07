package ACP3;

class FrontController
{
    private Dispatcher Dispatcher;

    public FrontController()
    {
        Dispatcher = new Dispatcher();
    }

    private boolean Authenticity()
    {
        System.out.println("Authentication successful.");
        return true;
    }

    private void Tracker(String request)
    {
        System.out.println("View Requested: " + request);
    }

    public void Request(String request)
    {
        Tracker(request);

        if(Authenticity())
        {
            Dispatcher.dispatch(request);
        }
    }
}
