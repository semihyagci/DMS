import java.util.ArrayList;
import java.util.Scanner;

public abstract class Document implements Component {
    protected boolean isSignedByManager;
    protected String type;
    protected String name;
    protected String address;
    protected ArrayList<User> users = new ArrayList<>();

    public Document(String type, String name, String address) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    abstract void Attach(User user);

    abstract void Detach(User user);

    abstract void Notify();

    public void Add(Component c) {
        System.out.println("Cannot add to a document.");
    }

    public void Remove(Component c) {
        System.out.println("Cannot remove from a document.");
    }
    public abstract void Display(int indent);
    abstract boolean isSignedByManager();

    public abstract Boolean verifyAllFields();

    abstract void setSignedByManager(boolean sign);
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class WordDocument extends Document {
    public WordDocument(String type, String name, String address) {
        super(type, name, address);
    }

    @Override
    void Attach(User user) {
        users.add(user);
    }

    @Override
    void Detach(User user) {
        users.removeIf(u -> u.getName().equals(user.getName()));
    }

    @Override
    void Notify() {
        for (User user : users) {
            user.Update(this);
        }
    }

    @Override
    public void Display(int indent) {
        for (int i = 1; i <= indent; i++) {
            System.out.print("-");
        }
        System.out.println(" " + name);
    }
    public boolean isSignedByManager() {
        return isSignedByManager;
    }

    @Override
    public Boolean verifyAllFields() {
        return (name != null) && (type != null) && (address != null);
    }

    @Override
    public void setSignedByManager(boolean sign) {
        isSignedByManager = sign;
    }
}

class PDFDocument extends Document {
    public PDFDocument(String type, String name, String address) {
        super(type, name, address);
    }

    @Override
    void Attach(User user) {
        users.add(user);
    }

    @Override
    void Detach(User user) {
        users.removeIf(u -> u.getName().equals(user.getName()));
    }

    @Override
    void Notify() {
        for (User user : users) {
            user.Update(this);
        }
    }

    @Override
    public void Display(int indent) {
        for (int i = 1; i <= indent; i++) {
            System.out.print("-");
        }
        System.out.println(" " + name);
    }

    @Override
    boolean isSignedByManager() {
        return isSignedByManager;
    }

    @Override
    public Boolean verifyAllFields() {
        return (name != null) && (type != null) && (address != null);
    }

    @Override
    public void setSignedByManager(boolean sign) {
        isSignedByManager = sign;
    }
}

class DocumentFactory {
    Scanner scan = new Scanner(System.in);
    public Document createDocument(String name, String address) {
        while (true) {
            System.out.print("Please specify the format of your document for the application: ");
            String format = scan.next();
            if (format.equalsIgnoreCase("wORd")) {
                return new WordDocument(format, name, address);
            }
            if (format.equalsIgnoreCase("pDf")) {
                return new PDFDocument(format, name, address);
            } else {
                System.out.println("Unsupported document format. Please enter a valid format. ");

            }
        }
    }
}

