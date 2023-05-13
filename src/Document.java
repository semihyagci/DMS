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
        if (status){
        sendToManager();
        Notify();
        }
        else{
            System.out.println("Rejected workorder");
        }
    }

    //factory method
    protected abstract Document createDocument(String type,String name,String address);
    protected abstract Boolean verifyAllFields();
    protected abstract void sendToManager();

    public boolean verifyIsSigned() {
        return isSignedByWorker;
    }

    public boolean isSignedByWorker() {
        return isSignedByWorker;
    }

    public void setSignedByWorker(boolean signedByWorker) {
        isSignedByWorker = signedByWorker;
    }

    public boolean isSignedByManager() {
        return isSignedByManager;
    }

    public void setSignedByManager(boolean signedByManager) {
        isSignedByManager = signedByManager;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
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

