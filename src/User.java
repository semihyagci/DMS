import java.util.ArrayList;

public class User extends People implements Observer {
    private boolean documentIsSignedByManager;
    private String documentName;

    private String documentAddress;


    public User(String name, int age, String address) {
        super(name, age, address);
    }

    //Observer
    public void Update(Document document) {
        //Update fields in here to show user
        documentName = document.getName();
        documentIsSignedByManager = document.isSignedByManager();
        documentAddress = document.getAddress();
        System.out.println("Your document with the \n name of: " + documentName + "\n address of: " + documentAddress + "  has been approved!\n");
    }

    //Invoker
    public void sendWorkOrder(Command command) {
        command.Execute();
        _commands.add(command);
    }

    private ArrayList<Command> _commands = new ArrayList<Command>();
}
