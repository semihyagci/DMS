import java.util.ArrayList;
import java.util.Stack;

public class EYTApplicationWorkOrder extends WorkOrder {
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;

    public EYTApplicationWorkOrder(String name, User user) {
        super(name, user);
        Database.createDepartmentsForEYTApplication();
        Database.createDocumentsForEYTApplication(user);

        departments = Database.EYTDepartments;
        documents = Database.EYTDocuments;

        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).Attach(user);
        }
    }
}