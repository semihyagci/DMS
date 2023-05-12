import java.util.ArrayList;

abstract class Document {
    //Subject of Observer(User)
    protected boolean isSignedByWorker;
    protected ArrayList<User> users = new ArrayList<User>();

    public Document() {
    }

    //Template Method
    public void checkingDocument(){
        Boolean status= verifyAllFields() && verifyIsSigned();
        sendToManager();
        Notify();
    }

    protected abstract Boolean verifyAllFields();
    protected abstract void sendToManager();

    protected boolean verifyIsSigned() {
        return isSignedByWorker;
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

class WordDocument extends Document {
    public WordDocument() {
        super();
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

class PDFDocument extends Document {
    public PDFDocument() {
        super();
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

    }

}