import java.util.ArrayList;

public class EYTApplicationWorkOrder extends WorkOrder{
    public EYTApplicationWorkOrder(String name,User user) {
        super(name,user);
        departments = Database.createDepartmentsForEYTApplication();
        documents=Database.createDocumentsForEYTApplication(user);
        for (int i=0;i<documents.size();i++){
            documents.get(i).Attach(user);
        }
    }
}