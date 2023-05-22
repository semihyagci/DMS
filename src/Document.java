import java.util.ArrayList;
import java.util.Scanner;

//subject of observer(user)
public abstract class Document {
    protected boolean isSignedByManager;
    protected String name;
    protected String address;
    protected ArrayList<User> users;

    public Document(String name, String address) {
        this.name = name;
        this.address = address;
        users = new ArrayList<>();
        isSignedByManager = false;
    }
    // Register The Observers
    public void Attach(User user) {
        users.add(user);
    }
    // Unregister
    public void Detach(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(user.getName())) {
                users.remove(i);
                return;
            }
        }
    }
    // Notify The Observers
    public void Notify() {
        for (User user : users) {
            user.Update(this);
        }
    }


    public boolean isSignedByManager() {
        return isSignedByManager;
    }

    public Boolean verifyAllFields() {
        return (name != null) && (address != null);
    }

    abstract public void setSignedByManager(boolean sign);

    abstract public void storeSignedFile(Document document);

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}

//Concrete Document
class WordDocument extends Document {
    public WordDocument(String name, String address) {
        super(name, address);
    }

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
//Concrete Document
class PDFDocument extends Document {
    public PDFDocument(String name, String address) {
        super(name, address);
    }

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

interface AbstractDocumentFactory{
    Document createDocument(String name, String address);
}

class DocumentFactory implements AbstractDocumentFactory {
    private static DocumentFactory uniqueDocumentFactoryInstance=null;

    private DocumentFactory() {
    }

    public static DocumentFactory getDocumentFactoryInstance(){
        if (uniqueDocumentFactoryInstance==null)
            uniqueDocumentFactoryInstance=new DocumentFactory();
        return uniqueDocumentFactoryInstance;
    }

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

