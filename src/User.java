//Interface of Observer
interface Observer {
    void UpdateDocument(Document document);
}

// User class(Invoker of Command Pattern)
// This class invokes the WorkOrder workflow.
class User implements Observer {
    //Attributes of User
    private String name;
    private int age;
    private String address;

    //Observer's state using for tracking fields in Update method
    private boolean documentIsSignedByManager;
    private String documentName;
    private String documentAddress;

    // Constructor of USer
    public User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    // Get username
    public String getName() {
        return name;
    }

    // Get user age
    public int getAge() {
        return age;
    }

    // Get user address
    public String getAddress() {
        return address;
    }

    //Observer's Update method for updating the Observer's states if Notify() method is invoked.
    public void UpdateDocument(Document document) {
        //Update fields in here to show User.
        documentName = document.getName();
        documentIsSignedByManager = document.isSignedByManager();
        documentAddress = document.getAddress();
        System.out.println("Your document with the \n name of: " + documentName + "\n address of: " + documentAddress + "  has been approved!\n");
    }

    //Invoking command method.
    public void sendWorkOrder(Command command) {
        command.startOperationOfWorkOrder();
    }
}
