import java.util.ArrayList;

public class User extends People implements Observer{
    private boolean isSignedByManagerInstance;
    private String documentName;


    public User(String name, int age, String address) {
        super(name, age, address);
    }

    //Observer
    public void Update(Document document) {
        //Update fields in here to show user
        documentName=document.getName();
        isSignedByManagerInstance=document.isSignedByManager();
        if (isSignedByManagerInstance)
            System.out.println("Your application with the document name of "+documentName+" has been approved!");
    }

    //Invoker
    public void sendWorkOrder(Command command){
        command.Execute();
        _commands.add(command);
    }

    private ArrayList<Command> _commands = new ArrayList<Command>();
}
