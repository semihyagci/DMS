import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

//Singleton-Facade
//This class as a work engine will route the
//work order from one department into another
public class DMS {
    private static DMS uniqueDMSInstance = null;
    private final User user;

    private DMS(User user) {
        this.user = user;
        System.out.println("***  Document Management System  ***\n");
        System.out.println("Hello " + this.user.getName() + " welcome to DMS...\n");
    }

    //Singleton getInstance method.
    public static DMS getDMSInstance(User user) {
        if (uniqueDMSInstance == null)
            uniqueDMSInstance = new DMS(user);
        return uniqueDMSInstance;
    }


    // DMS creating the structure of selected application.
    public void createApplication(User user) {
        Scanner input = new Scanner(System.in);
        WorkOrder workOrder;
        System.out.println("Press 1 for Vacation Application\nPress 2 for EYT Application\nPress -1 for exit.");
        int choice = input.nextInt();
        //CHOOSING THE APPLICATION
        while (choice != -1) {
            //CHOOSING VACATION APPLICATION
            if (choice == 1) {
                workOrder = new VacationApplicationWorkOrder("Vacation Request", user);
                WorkOrder vacationHrCheckSubWorkOrder = new VacationApplicationHRWorkOrder("Vacation Check Request Human Resources", user);
                WorkOrder vacationAdministrationCheckSubWorkOrder = new VacationApplicationAdministrationWorkOrder("Vacation Check Request Administration", user);
                workOrder.Add(vacationHrCheckSubWorkOrder);
                workOrder.Add(vacationAdministrationCheckSubWorkOrder);
                System.out.println();
                workOrder.Display(1);
                System.out.println();
                System.out.println("You choose to create Vacation Application Request. DMS is filling the required areas of the document with your informations automatically.\n");
                user.sendWorkOrder(workOrder);
                boolean check = WorkOrder.checkingAllDocuments(workOrder);
                if (check) {
                    System.out.println("Your work order is completely approved!");
                } else {
                    System.out.println("We are sorry for inform you that your workorder has been rejected because of these documents.");
                    System.out.println("---------------------------------------------------");
                    for (Document document : WorkOrder.rejectedDocuments) {
                        System.out.println(document.getName());
                    }
                    System.out.println("---------------------------------------------------");

                }
                break;
            } 
            // CHOOSING EYT APPLICATION
            else if (choice == 2) {
                workOrder = new EYTApplicationWorkOrder("EYT Request", user);
                WorkOrder eytSecondWorkOrder = new EYTApplicationSSAWorkOrder("EYT Request SSA Check", user);
                workOrder.Add(eytSecondWorkOrder);
                System.out.println();
                workOrder.Display(1);
                System.out.println("You choose to create EYT Application Request. DMS is filling the required areas of the document with your informations automatically.\n");
                user.sendWorkOrder(workOrder);
                boolean check = WorkOrder.checkingAllDocuments(workOrder);
                if (check) {
                    System.out.println("Your work order is completely approved!");
                } else {
                    System.out.println("We are sorry for inform you that your workorder has been rejected because of these documents.");
                    System.out.println("---------------------------------------------------");
                    for (Document document : WorkOrder.rejectedDocuments) {
                        System.out.println(document.getName());
                    }
                    System.out.println("---------------------------------------------------");

                }
                break;
            } else {
                System.out.println("Invalid choice.\nPress 1 for Vacation Application\nPress 2 for EYT Application\nPress -1 to exit.");
                choice = input.nextInt();
            }

        }
    }
    //MAIN CLASS
    public static void main(String[] args) {
        User user = new User("Semih", 19, "Buca");
        DMS dms = DMS.getDMSInstance(user);
        dms.createApplication(user);
    }

}

//This class is not related with the patterns which are developed in the project.
//This class is just creating departments and regarding documents needed in the related workorder,Also we store the signed documents in this central database class.
class Database {
    public static ArrayList<Document> signedDocuments = new ArrayList<>();
    public static ArrayList<Document> VacationDocuments = new ArrayList<>();
    public static Stack<Department> HRVacationDepartments = new Stack<>();
    public static Stack<Department> AdministrationVacationDepartments = new Stack<>();
    public static ArrayList<Document> EYTDocuments = new ArrayList<>();
    public static Stack<Department> PublicRelationsEYTDepartments = new Stack<>();
    public static Stack<Department> SSAEYTDepartments = new Stack<>();

    // METHOD FOR STORING SIGNED DOCUMENTS
    public static void storeSignedDocument(Document document) {
        if (signedDocuments.contains(document.getName())) {
            for (int i = 0; i < signedDocuments.size(); i++) {
                if (signedDocuments.get(i).getName().equals(document.getName())) {
                    signedDocuments.set(i, document);
                }
            }
        } else {
            signedDocuments.add(document);
        }
    }
    //CREATE HR DEP. FOR V.A.
    public static Stack<Department> createHrDepartmentsForVacationApplication() {
        Department hr = new HumanResources("Human Resources", new Manager("Didem Tiknaz"));
        HRVacationDepartments.add(hr);
        return HRVacationDepartments;
    }
    //CREATE ADMINISTRATION DEP. FOR V.A.
    public static Stack<Department> createAdministrationDepartmentsForVacationApplication() {
        Department softwareEngineeringDepartment = new EngineeringDepartment("Software Engineering Department", new Manager("Senem Kumova Metin"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery", new Manager("Yasar Guneri Sahin"));
        Department rectorate = new Rectorate("IUE Rectorate", new Manager("Murat Askar"));

        AdministrationVacationDepartments.add(softwareEngineeringDepartment);
        AdministrationVacationDepartments.add(engineeringDeanery);
        AdministrationVacationDepartments.add(rectorate);
        return AdministrationVacationDepartments;
    }
    // CREATE PUBLIC RELATIONS DEP. FOR EYT A.
    public static Stack<Department> createPublicRelationsDepartmentsForEYTApplication() {
        Department Public_Relations = new EYTFirstDep("Public Relations Department", new Manager("Yasin Çolak"));
        PublicRelationsEYTDepartments.add(Public_Relations);
        return PublicRelationsEYTDepartments;
    }   
    // CREATE SSA DEP. FOR EYT A.
    public static Stack<Department> createSSADepartmentsForEYTApplication() {
        Department Payment_Department = new EYTThirdDep("Payment Department", new Manager("Hasan Yücetaş"));
        Department Tax_Department = new EYTThirdDep("Tax Department", new Manager("Hande Baş"));

        SSAEYTDepartments.add(Payment_Department);
        SSAEYTDepartments.add(Tax_Department);
        return SSAEYTDepartments;
    }
    // CREATE DOCUMENTS FOR EYT APPLICATION
    public static void createDocumentsForEYTApplication(User user) {
        System.out.println("For this application; you need to get these 3 documents signed.\n");
        System.out.println("""
                Formal Age Document
                Formal Working Days Document
                Retirement Insurance Payment Document
                """);
        //DOCUMENT FACTORY
        DocumentFactory documentFactory = DocumentFactory.getDocumentFactoryInstance();
        Document doc1 = documentFactory.createDocument("Formal Age Document", user.getAddress());
        Document doc2 = documentFactory.createDocument("Formal Working Days Document", user.getAddress());
        Document doc3 = documentFactory.createDocument("Retirement Insurance Payment Document", user.getAddress());
        EYTDocuments.add(doc1);
        EYTDocuments.add(doc2);
        EYTDocuments.add(doc3);

    }

    // CREATE DOCUMENTS FOR VACATION APPLICATION
    public static void createDocumentsForVacationApplication(User user) {
        System.out.println("For this application; you need to get these 3 documents signed.\n");
        System.out.println("""
                Vacation Permission From Administration Document
                Application Form
                Legal Working Day Document
                """);
        // DOCUMENT FACTORY
        DocumentFactory documentFactory = DocumentFactory.getDocumentFactoryInstance();
        Document doc1 = documentFactory.createDocument("Vacation Permission from Administration Document", user.getAddress());
        Document doc2 = documentFactory.createDocument("Application Form", user.getAddress());
        Document doc3 = documentFactory.createDocument("Legal Working Day Document", user.getAddress());
        VacationDocuments.add(doc1);
        VacationDocuments.add(doc2);
        VacationDocuments.add(doc3);
    }
    // PARSING THE DOCUMENTS FOR VACATION APPLICATION
    public static ArrayList<Document> dividingSpecificPartOfTheVacationDocumentsList(int firstIndex, int lastIndex) {
        ArrayList<Document> temp = new ArrayList<>();
        for (int i = firstIndex; i <= lastIndex; i++) {
            temp.add(VacationDocuments.get(i));
        }
        return temp;
    }
    // PARSING THE DOCUMENTS FOR EYT APPLICATION
    public static ArrayList<Document> dividingSpecificPartOfTheEYTDocumentsList(int firstIndex, int lastIndex) {
        ArrayList<Document> temp = new ArrayList<>();
        for (int i = firstIndex; i <= lastIndex; i++) {
            temp.add(EYTDocuments.get(i));
        }
        return temp;
    }
}


