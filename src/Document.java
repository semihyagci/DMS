import java.util.ArrayList;
import java.util.Scanner;

//SUBJECT OF OBSERVER (USER)
public abstract class Document {
    // ATTRIBUTES
    protected boolean isSignedByManager;
    protected String name;
    protected String address;
    protected ArrayList<User> users;
    //CONSTRUCTOR
    public Document(String name, String address) {
        this.name = name;
        this.address = address;
        users = new ArrayList<>();
        isSignedByManager = false;
    }
    // REGISTERING THE OBSERVERS
    public void Attach(User user) {
        users.add(user);
    }
    // UNREGISTERING
    public void Detach(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(user.getName())) {
                users.remove(i);
                return;
            }
        }
    }
    // NOTIFY ALL THE OBSERVERS TO UPDATE
    public void Notify() {
        for (User user : users) {
            user.Update(this);
        }
    }

    // CHECK FOR IS MANAGER SIGNed
    public boolean isSignedByManager() {
        return isSignedByManager;
    }
    // VERIFY THAT ALL FIELDS ARE FILLED
    public Boolean verifyAllFields() {
        return (name != null) && (address != null);
    }
    // SETTING THE MANAGER SIGN FOR DOCUMENT
    abstract public void setSignedByManager(boolean sign);

    // STORES THE SIGNED FILES (DOCUMENTS)
    abstract public void storeSignedFile(Document document);

    // NAME GETTER
    public String getName() {
        return name;
    }
    // ADDRESS GETTER
    public String getAddress() {
        return address;
    }
}

//CONCRETE DOCUMENT
class WordDocument extends Document {
    public WordDocument(String name, String address) {
        super(name, address);
    }
    // OVERRIDED METHODS OF DOCUMENT CLASS
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

    @Override
    public void storeSignedFile(Document document) {
        System.out.println("The signed WORD document is storing in the central database...\n");
        Database.storeSignedDocument(document);
    }

}
//CONCRETE DOCUMENT 
class PDFDocument extends Document {
    public PDFDocument(String name, String address) {
        super(name, address);
    }
    // OVERRIDED METHODS OF DOCUMENT CLASS
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

    @Override
    public void storeSignedFile(Document document) {
        System.out.println("The signed PDF document is storing in the central database...\n");
        Database.storeSignedDocument(document);
    }
}

// ABSTRACT FACTORY INTERFACE FOR CONCRETEFACTORY
interface AbstractDocumentFactory{
    // CREATE DOCUMENT METHOD OF ABSTRACT FACTORY
    Document createDocument(String name, String address);
}

// CONCRETEFACTORY (CONCRETECREATOR)
class DocumentFactory implements AbstractDocumentFactory {
    private static DocumentFactory uniqueDocumentFactoryInstance=null;

    private DocumentFactory() {
    }

    public static DocumentFactory getDocumentFactoryInstance(){
        if (uniqueDocumentFactoryInstance==null)
            uniqueDocumentFactoryInstance=new DocumentFactory();
        return uniqueDocumentFactoryInstance;
    }
    // RETURN AN INSTANCE OF CONCRETE PRODUCT 
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

