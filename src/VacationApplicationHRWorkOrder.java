import java.util.ArrayList;
import java.util.Stack;

public class VacationApplicationHRWorkOrder extends WorkOrder {
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;

    public VacationApplicationHRWorkOrder(String name, User user) {
        super(name, user);
        departments = Database.createHrDepartmentsForVacationApplication();
        documents = Database.dividingSpecificPartOfTheVacationDocumentsList(1, 2);
        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).Attach(user);
        }
    }

    @Override
    public void Execute() {
        System.out.println("Your application forwarded to " + departments.get(0).getDepartmentName() + " for approval.\n");
        for (int i = 0; i < departments.size(); i++) {
            departments.get(i).Action(documents);
            Boolean checkDocumentsAreApproved = checkingAllDocuments(i, documents);

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
    }

}
