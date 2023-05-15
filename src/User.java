import java.util.ArrayList;

public class User extends People implements Observer{
    public User(String name, int age, String address) {
        super(name, age, address);
    }

    //Observer
    public void Update(Document document) {
        //Update fields in here to show user
    }

    //Invoker
    public void sendWorkOrder(Command command){
        command.Execute();
        _commands.add(command);
    }

    private ArrayList<Command> _commands = new ArrayList<Command>();
}
