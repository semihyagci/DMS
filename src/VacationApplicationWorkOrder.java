public class VacationApplicationWorkOrder extends WorkOrder {

    public VacationApplicationWorkOrder(String name,User user) {
        super(name,user);
        departments = Database.createDepartmentsForVacationApplication();
        documents= Database.createDocumentsForVacationApplication(user);
        for (int i=0;i<documents.size();i++){
            documents.get(i).Attach(user);
        }
    }
}
