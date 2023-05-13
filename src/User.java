import java.util.ArrayList;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
