import javax.print.Doc;
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

    public void Attach(User user) {
        users.add(user);
    }

    public void Detach(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName() == user.getName()) {
                users.remove(i);
                return;
            }
        }
    }

    public void Notify() {
        for (int i = 0; i < users.size(); i++) {
            users.get(i).Update(this);
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


class WordDocument extends Document {
    public WordDocument(String name, String address) {
        super(name, address);
    }

    @Override
    public void setSignedByManager(boolean sign) {
        if (sign) {
            System.out.println("The WORD document with the name of " + name + " is signed by the manager.\n");
            storeSignedFile(this);
        } else {
            System.out.println("The WORD document is rejected by the manager.\n");
        }
        isSignedByManager = sign;
    }

    @Override
    public void storeSignedFile(Document document) {
        System.out.println("The signed WORD document is storing in the central database...\n");
        Database.storeSignedDocument(document);
    }

}

class PDFDocument extends Document {
    public PDFDocument(String name, String address) {
        super(name, address);
    }

    @Override
    public void setSignedByManager(boolean sign) {
        if (sign) {
            System.out.println("The PDF document with the name of " + name + " is signed by the manager.\n");
            storeSignedFile(this);
        } else {
            System.out.println("The PDF document is rejected by the manager.\n");
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
            System.out.print("Please specify the format of your document for the application: ");
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

