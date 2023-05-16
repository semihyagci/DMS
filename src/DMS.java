import java.util.ArrayList;
import java.util.Stack;

//Facade
//This class as a work engine will route the
//work order from one department into another
public class DMS {
    public DMS() {
    }

    public void createApplicationForVacation(User user, Document document){
        System.out.println("Hello "+user.getName()+" welcome to Document Management System...");
        ArrayList<Document> a= new ArrayList<Document>();
        a.add(document);
        document.Attach(user);
        user.sendWorkOrder(new VacationApplicationWorkOrder("Vacation Request",a));
    }

}
