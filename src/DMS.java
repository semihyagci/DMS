import java.util.ArrayList;
import java.util.Stack;

//Facade
//This class as a work engine will route the
//work order from one department into another
public class DMS {
    private Stack<Department> departments = new Stack<Department>();
    DMS(Stack<Department> departments){
        this.departments = departments;
    }

    public boolean IsTransmitted(Document document){
        boolean transmitted = false;
        for (int i=0; i< departments.size();i++){
            if (document.isSignedByManager) {
                transmitted = true;
                break;
            }
        }
        return transmitted;
    }
    public void createApplication(User user, Document document, Stack<Department> departments){
        System.out.println("Hello "+user.getName()+" welcome to Document Management System...");
        ArrayList<Document> a= new ArrayList<Document>();
        a.add(document);
        document.Attach(user);
        user.sendWorkOrder(new WorkOrder("Vacation Request",a,departments));
    }
}
