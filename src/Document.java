import java.util.ArrayList;

abstract class Document {
    //Subject of Observer(User)
    protected boolean isSignedByWorker;
    protected boolean isSignedByManager;
    protected String type;
    protected String name;
    protected String address;
    protected ArrayList<User> users = new ArrayList<User>();

    public Document(String type,String name,String address) {
        this.isSignedByWorker=false;
        this.isSignedByManager=false;
        this.name=name;
        this.address=address;
        this.type=type;
    }

    //Template Method
    public void checkingDocument(){
        Boolean status= verifyAllFields() && verifyIsSigned();
        sendToManager();
        Notify();
    }

    //factory method
    protected abstract Document createDocument(String type,String name,String address);
    protected abstract Boolean verifyAllFields();
    protected abstract void sendToManager();

    public boolean verifyIsSigned() {
        return isSignedByWorker;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public void Attach (User user) {
        users.add(user);
    }

    public void Detach (User user) {
        for (int i = 0; i< users.size(); i++) {
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

    public void hook(){

    }
}

class ConcreteDocument extends Document {
    public ConcreteDocument(String type,String name,String address) {
        super(type,name,address);
    }

    @Override
    protected Document createDocument(String type,String name,String address) {
        return new ConcreteDocument(type,name,address);
    }

    @Override
    protected Boolean verifyAllFields() {
        return null;
    }

    @Override
    protected void sendToManager() {

    }


    @Override
    public void hook() {
        // for hooking
    }
}

