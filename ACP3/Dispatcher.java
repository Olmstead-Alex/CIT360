package ACP3;

class Dispatcher
{
    private GamerView gamerView;
    private DeveloperView developerView;

    public Dispatcher()
    {
        gamerView = new GamerView();
        developerView = new DeveloperView();
    }

    public void dispatch(String request)
    {
        if(request.equalsIgnoreCase("Game"))
        {
            gamerView.display();
        }
        else
        {
            developerView.display();
        }
    }
}
