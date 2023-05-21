import java.util.ArrayList;
import java.util.Stack;

interface Command {
    void Execute();
}

// This is the "Composite"
abstract class WorkOrder implements Command {
    protected String name;
    protected ArrayList<WorkOrder> subWorkOrders;
    protected User workorderCreator;
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;

    public WorkOrder(String name, User workorderCreator) {
        this.name = name;
        this.workorderCreator = workorderCreator;
        subWorkOrders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void Add(WorkOrder d) {
        subWorkOrders.add(d);
    }

    public void Remove(WorkOrder d) {
        for (int i = 0; i < subWorkOrders.size(); i++) {
            if (subWorkOrders.get(i).getName() == d.getName()) {
                subWorkOrders.remove(i);
                return;
            }
        }
    }

    public void Display(int indent) {
        for (int i = 1; i <= indent; i++) System.out.print("-");
        System.out.println("+ " + getName());
        for (int i = 0; i < subWorkOrders.size(); i++) {
            subWorkOrders.get(i).Display(indent + 2);
        }
    }

    @Override
    public void Execute() {
        if (documents==null){
        for (int i = 0; i < subWorkOrders.size(); i++) {
            subWorkOrders.get(i).Execute();
        }
        }else {
            System.out.println("Your application forwarded to " + departments.get(0).getDepartmentName() + " for approval.\n");
            for (int i = 0; i < departments.size(); i++) {
                departments.get(i).Action(documents);
                Boolean checkDocumentsAreApproved = checkingAllDocuments();

                if (departments.get(i).equals(departments.lastElement())) {
                    if (checkDocumentsAreApproved) {
                        System.out.println("Your application is approved!");
                    } else {
                        System.out.println("@@@@@@@@@@@@@ REJECTED WORKORDER @@@@@@@@@@@@@");
                        System.out.println("We are sorry for inform you that your workorder has been rejected because some of the documents are not suitable for our procedures. Please check your documents and make the corrections according to our rules and apply again. ");
                        System.exit(0);
                    }
                    break;
                }

                if (checkDocumentsAreApproved) {
                    System.out.println("All the documents are okay to moving forward to upper departments to confirm. \n");
                } else {
                    System.out.println("@@@@@@@@@@@@@ REJECTED WORKORDER @@@@@@@@@@@@@");
                    System.out.println("We are sorry for inform you that your workorder has been rejected because some of the documents are not suitable for our procedures. Please check your documents and make the corrections according to our rules and apply again. ");
                    System.exit(0);
                }
                System.out.println("Your application forwarded to " + departments.get(i + 1).getDepartmentName() + " for approval.\n");
            }
            if (subWorkOrders!=null){
                for (int i = 0; i < subWorkOrders.size(); i++) {
                    subWorkOrders.get(i).Execute();
                }
            }
        }
    }

    public boolean checkingAllDocuments() {
        boolean checkingAllDocuments = true;
        for (int j = 0; j < documents.size(); j++) {
            if (!documents.get(j).isSignedByManager()) {
                checkingAllDocuments = false;
            }
        }
        return checkingAllDocuments;
    }
}

class VacationApplicationWorkOrder extends WorkOrder {
    public VacationApplicationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        Database.createDocumentsForVacationApplication(workorderCreator);
    }
}

class VacationApplicationHRWorkOrder extends WorkOrder {
    public VacationApplicationHRWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        departments = Database.createHrDepartmentsForVacationApplication();
        documents = Database.dividingSpecificPartOfTheVacationDocumentsList(1, 2);
        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).Attach(workorderCreator);
        }
    }

}
class VacationApplicationAdministrationWorkOrder extends WorkOrder {
    public VacationApplicationAdministrationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        departments = Database.createAdministrationDepartmentsForVacationApplication();
        documents = Database.dividingSpecificPartOfTheVacationDocumentsList(0, 0);
        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).Attach(workorderCreator);
        }
    }

}

class EYTApplicationWorkOrder extends WorkOrder {
    public EYTApplicationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        Database.createDepartmentsForEYTApplication();
        Database.createDocumentsForEYTApplication(workorderCreator);

        departments = Database.EYTDepartments;
        documents = Database.EYTDocuments;

        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).Attach(workorderCreator);
        }
    }
}
