import java.util.ArrayList;
import java.util.Stack;

public class VacationApplicationWorkOrder extends WorkOrder{

    public VacationApplicationWorkOrder(String name, ArrayList<Document> docs) {
        super(name, docs);
        departments=Database.createDepartmentsForVacationApplication();
    }
}
