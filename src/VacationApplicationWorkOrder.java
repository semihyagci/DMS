public class VacationApplicationWorkOrder extends WorkOrder {

    public VacationApplicationWorkOrder(String name, User user) {
        super(name, user);
        Database.createDocumentsForVacationApplication(user);
    }


}
