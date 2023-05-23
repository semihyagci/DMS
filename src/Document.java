import java.util.ArrayList;
import java.util.Scanner;

//Subject of Observer (user)
public abstract class Document {
    // Attributes
    protected boolean isSignedByManager;
    protected String name;
    protected String address;
    protected ArrayList<User> users;
    //Constructor
    public Document(String name, String address) {
        this.name = name;
        this.address = address;
        users = new ArrayList<>();
        isSignedByManager = false;
    }
    // Registering the Observers
    public void Attach(User user) {
        users.add(user);
    }
    // Unregistering
    public void Detach(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(user.getName())) {
                users.remove(i);
                return;
            }
        }
    }
    // Notify all the Observers to update
    public void Notify() {
        for (User user : users) {
            user.Update(this);
        }
    }

    // Check for is manager signed
    public boolean isSignedByManager() {
        return isSignedByManager;
    }
    // Verify all fields are filled
    public Boolean verifyAllFields() {
        return (name != null) && (address != null);
    }
    // Setting the manager sign for document
    abstract public void setSignedByManager(boolean sign);

    // Store the signed files (documents)
    abstract public void storeSignedFile(Document document);

    // Name getter
    public String getName() {
        return name;
    }
    // Address getter
    public String getAddress() {
        return address;
    }
}

//Concrete document
class WordDocument extends Document {
    //Constructor
    public WordDocument(String name, String address) {
        super(name, address);
    }
    // Overrided methods of document class
    @Override
    public void setSignedByManager(boolean sign) {
        if (sign) {
            System.out.println("Current manager has approved the WORD document with the name of "+name+".\n");
            storeSignedFile(this);
        } else {
            System.out.println("Current manager has rejected the WORD document with the name of "+name+".\n");
        }
        isSignedByManager = sign;
    }
    // Overrided methods of document class
    @Override
    public void storeSignedFile(Document document) {
        System.out.println("The signed WORD document is storing in the central database...\n");
        Database.storeSignedDocument(document);
    }

}
//Concrete Document 
class PDFDocument extends Document {
    public PDFDocument(String name, String address) {
        super(name, address);
    }
    // Overrided methods of document class
    @Override
    public void setSignedByManager(boolean sign) {
        if (sign) {
            System.out.println("Current manager has approved the PDF document with the name of "+name+".\n");
            storeSignedFile(this);
        } else {
            System.out.println("Current manager has rejected the PDF document with the name of "+name+".\n");
        }
        isSignedByManager = sign;
    }
    // Overrided methods of document class
    @Override
    public void storeSignedFile(Document document) {
        System.out.println("The signed PDF document is storing in the central database...\n");
        Database.storeSignedDocument(document);
    }
}
// Abstract factory interface for Concretefactory
interface AbstractDocumentFactory{
    // Create document method of Abstractfactory
    Document createDocument(String name, String address);
}

// ConcreteFactory (ConcreteCreator)
class DocumentFactory implements AbstractDocumentFactory {
    private static DocumentFactory uniqueDocumentFactoryInstance=null;
    // This is private constructor to prevent usage of "new" keyword for singleton pattern
    private DocumentFactory() {
    }

    public static DocumentFactory getDocumentFactoryInstance(){
        if (uniqueDocumentFactoryInstance==null)
            uniqueDocumentFactoryInstance=new DocumentFactory();
        return uniqueDocumentFactoryInstance;
    }
    // Return an instance of concrete product
    @Override
    public Document createDocument(String name, String address) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Please specify the format of the document "+name+".\n");
            String format = scan.next();
            if (format.equalsIgnoreCase("wORd")) {
                return new WordDocument(name, address);
            }
            if (format.equalsIgnoreCase("pDf")) {
                return new PDFDocument(name, address);
            } else {
                System.out.println("Unsupported document format. Please enter a valid format. \n");
            }
        }
    }
}

