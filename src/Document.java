import java.util.ArrayList;

    //Subject of Observer(User)
    //This is also a leaf node of an composite structure
class Document implements Component{
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


    //factory method
    public Document createDocument(String type,String name,String address){
        return new Document(type,name,address);
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

    public void Add(Component c) {
        System.out.println("Cannot add to a document.");
    }
        public  void Remove(Component c) {
            System.out.println("Cannot remove from a document.");
        }
        public void Display(int indent) {
            for(int i = 1;i <= indent;i++) 	System.out.print("-");
            System.out.println(" "  + name);
        }

        @Override
        public String getName() {
            return name;
        }

        public boolean isSignedByManager() {
            return isSignedByManager;
        }

        public void setSignedByWorker(boolean b) {
            isSignedByWorker=true;
        }

        public Boolean verifyAllFields() {
            return (name!=null)&&(type!=null)&&(address!=null);
        }

        public boolean verifyIsSigned() {
            return isSignedByWorker;
        }

        public void setSignedByManager(boolean b) {
            isSignedByManager=b;
        }
    }


