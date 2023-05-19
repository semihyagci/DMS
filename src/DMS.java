import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

//Facade
//This class as a work engine will route the
//work order from one department into another
public class DMS {
    public DMS() {
    }

    public void createApplicationForVacation(User user, ArrayList<Document> documents) {
        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).Attach(user);
        }
        user.sendWorkOrder(new VacationApplicationWorkOrder("Vacation Request", documents));
    }

}
