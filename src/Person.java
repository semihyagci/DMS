import java.util.ArrayList;
import java.util.Scanner;

abstract public class Person {
    private String name;
    private int age;
    private String address;

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}

interface Observer {
    void Update(Document document);
}

class User extends Person implements Observer {
    //Observer's tracking fields in Update method
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

class Manager extends Person {
    private int documentIncrement = 1;

    public Manager(String name, int age, String address) {
        super(name, age, address);
    }

    public void signingByManager(Document document) {
        Scanner scan = new Scanner(System.in);
        int sign;
        System.out.println("Your application's " + (documentIncrement) + ". document is  reviewing by " + getName() + " right now.\n");
        System.out.println("Switching to manager...\n");
        System.out.print("Press (1) to sign or press (0) to reject: ");
        sign = scan.nextInt();
        while (true) {
            if (sign == 1) {
                document.setSignedByManager(true);
                break;
            }
            if (sign == 0) {
                document.setSignedByManager(false);
                break;
            } else {
                System.out.println("Invalid choice. Try again\n");
                System.out.print("Press (1) to sign or press (0) to reject: ");
                sign = scan.nextInt();
            }
        }
        documentIncrement++;
    }
}
