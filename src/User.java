interface Observer {
    void Update(Document document);
}
// USER CLASS
class User implements Observer {
    //ATTRIBUTES
    private final String name;
    private int age;
    private String address;

    //Observer's state using for tracking fields in Update method
    private boolean documentIsSignedByManager;
    private String documentName;
    private String documentAddress;

    // CONSTRUCTOR OF USER
    public User(String name, int age, String address) {
        this.name=name;
        this.age=age;
        this.address=address;
    }
    // GET USER NAME
    public String getName() {
        return name;
    }
    // GET USER AGE
    public int getAge() {
        return age;
    }
    // GET USER ADDRESS
    public String getAddress() {
        return address;
    }

    //OBSERVER
    public void Update(Document document) {
        //UPDATE FIELDS IN HERE TO SHOW USER
        documentName = document.getName();
        documentIsSignedByManager = document.isSignedByManager();
        documentAddress = document.getAddress();
        System.out.println("Your document with the \n name of: " + documentName + "\n address of: " + documentAddress + "  has been approved!\n");

    }

    //INVOKER
    public void sendWorkOrder(Command command) {
        command.Execute();
    }
}
