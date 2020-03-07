package ACP3;

class FrontControllerPattern
{
    public static void main(String[] args)
    {
        FrontController frontController = new FrontController();
        System.out.println("Let's take a look at the Gamer View");
        frontController.Request("Gamer");

        System.out.println("Now, let's take a look at the Developer View");
        frontController.Request("Developer");

    }
}
