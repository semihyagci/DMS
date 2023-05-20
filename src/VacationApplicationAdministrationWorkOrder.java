public class VacationApplicationAdministrationWorkOrder extends WorkOrder {

    public VacationApplicationAdministrationWorkOrder(String name, User user) {
        super(name,user);
        departments = Database.createDepartmentsForVacationApplication();
        documents= Database.createDocumentsForVacationApplication(user);
        for (int i=0;i<documents.size();i++){
            documents.get(i).Attach(user);
        }
    }
}
