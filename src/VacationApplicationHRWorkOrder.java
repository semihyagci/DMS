public class VacationApplicationHRWorkOrder extends WorkOrder{
    public VacationApplicationHRWorkOrder(String name, User user) {
        super(name, user);
        departments = Database.createHrDepartmentsForVacationApplication();
        documents = Database.getVacationDocuments;
    }
}
