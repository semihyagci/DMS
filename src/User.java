interface Observer {
    void Update(Document document);
}
// User class
class User implements Observer {
    private final String name;

    private int age;

    private String address;

    //Observer's state using for tracking fields in Update method
    private boolean documentIsSignedByManager;
    private String documentName;
    private String documentAddress;


    public User(String name, int age, String address) {
        this.name=name;
        this.age=age;
        this.address=address;
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
    }
}
