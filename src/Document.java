import java.util.ArrayList;
import java.util.Scanner;


//Document Class
//Subject of Observer(User)
public abstract class Document {
    // Attributes for Document
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

    // Attaching the Observers with the documents.
    public void Attach(User user) {
        users.add(user);
    }

    // Detaching the Observers with the documents.
    public void Detach(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(user.getName())) {
                users.remove(i);
                return;
            }
        }
    }

    // Notify all the Observers to update their state.
    public void Notify() {
        for (User user : users) {
            user.UpdateDocument(this);
        }
    }

    // Method for checking for is manager signed the document
    public boolean isSignedByManager() {
        return isSignedByManager;
    }

    //Method for verifying all fields are filled
    public Boolean verifyAllFields() {
        return (name != null) && (address != null);
    }

    //Method for setting the manager sign for document
    abstract public void setSignedByManager(boolean sign);

    //Method for storing the signed documents
    abstract public void storeSignedFile(Document document);

    //Name getter
    public String getName() {
        return name;
    }

    //Address getter
    public String getAddress() {
        return address;
    }
}

//Concrete Product(WordDocument) of Factory
class WordDocument extends Document {
    //Constructor
    public WordDocument(String name, String address) {
        super(name, address);
    }
    // Overrided methods of document class

    //Method for signing WORD document differently.
    @Override
    public void setSignedByManager(boolean sign) {
        if (sign) {
            System.out.println("Current manager has approved the WORD document with the name of " + name + ".\n");
            storeSignedFile(this);
        } else {
            System.out.println("Current manager has rejected the WORD document with the name of " + name + ".\n");
        }
        isSignedByManager = sign;
    }

    //Method for storing the WORD document differently.
    @Override
    public void storeSignedFile(Document document) {
        System.out.println("The signed WORD document is storing in the central database...\n");
        Database.storeSignedDocument(document);
    }

}

//Concrete Product(PDFDocument) of Factory
class PDFDocument extends Document {
    //Constructor
    public PDFDocument(String name, String address) {
        super(name, address);
    }
    //Overrided methods of document class

    //Method for signing PDF document differently.
    @Override
    public void setSignedByManager(boolean sign) {
        if (sign) {
            System.out.println("Current manager has approved the PDF document with the name of " + name + ".\n");
            storeSignedFile(this);
        } else {
            System.out.println("Current manager has rejected the PDF document with the name of " + name + ".\n");
        }
        isSignedByManager = sign;
    }

    //Method for storing the PDF document differently.
    @Override
    public void storeSignedFile(Document document) {
        System.out.println("The signed PDF document is storing in the central database...\n");
        Database.storeSignedDocument(document);
    }
}

// AbstractCreator Interface
interface AbstractDocumentFactory {
    // Create document method of Abstractfactory
    Document createDocument(String name, String address);
}

//ConcreteFactory (ConcreteCreator) - Singleton
//This class is responsible with Product(Document) creation.
//There should be only one instance of DocumentFactory for this system, we controlled the object creation of DocumentFactory with making this class Singleton.
class DocumentFactory implements AbstractDocumentFactory {
    //Singleton's uniqueInstance
    private static DocumentFactory uniqueDocumentFactoryInstance = null;

    // This is private constructor to prevent usage of "new" keyword for Singleton pattern
    private DocumentFactory() {
    }

    //Singleton's getInstance Method
    public static DocumentFactory getDocumentFactoryInstance() {
        if (uniqueDocumentFactoryInstance == null)
            uniqueDocumentFactoryInstance = new DocumentFactory();
        return uniqueDocumentFactoryInstance;
    }

    //Create and return an instance of concrete product
    @Override
    public Document createDocument(String name, String address) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Please specify the format of the document " + name + ".\n");
            String format = scan.next();
            //The equalsIgnoreCase() method compares two strings, ignoring lower case and upper case differences. This method returns true if the strings are equal, and false if not.
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

