import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

//Singleton-Facade
//This class is responsible for making the application easy to use. There should be only one instance from it.
public class DMS {
    //Singleton's uniqueInstance
    private static DMS uniqueDMSInstance = null;
    private User user;

    //This is private constructor to prevent usage of "new" keyword for Singleton pattern
    private DMS() {
        System.out.println("WELCOME TO DOCUMENT MANAGEMENT SYSTEM\n");
        System.out.println("YOU ARE DIRECTED TO REGISTRATION PROCESS\n");
    }

    //Singleton getInstance method.
    public static DMS getDMSInstance() {
        if (uniqueDMSInstance == null)
            uniqueDMSInstance = new DMS();
        return uniqueDMSInstance;
    }

    //Registeration method for user
    public void register() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = input.next();
        System.out.println("Enter your age:");
        int age = input.nextInt();
        System.out.println("Enter your address");
        String address = input.next();

        if (name != null && address != null) {
            User user = new User(name, age, address);
            this.user = user;
            System.out.println("***  Document Management System  ***\n");
            System.out.println("Hello " + this.user.getName() + " welcome to DMS...\n");
        } else {
            System.out.println("Your inputs are invalid please try again.");
            register();
        }
    }

    // DMS's creating the WorkOrder of the selected application method.
    public void createApplication() {
        Scanner input = new Scanner(System.in);
        WorkOrder workOrder;
        System.out.println("Press 1 for Vacation Application\nPress 2 for Retirement Application\nPress -1 for exit.");
        //Choosing the application type
        int choice = input.nextInt();
        while (choice != -1) {
            //Choosing Vacation Application
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
                    System.out.println("Your application: " + workOrder.getName() + " is completely approved!");
                } else {
                    System.out.println("We are sorry for inform you that your application: " + workOrder.getName() + " has been rejected because of these documents.");
                    System.out.println("---------------------------------------------------");
                    for (Document document : WorkOrder.rejectedDocuments) {
                        System.out.println(document.getName());
                    }
                    System.out.println("---------------------------------------------------");

                }
                break;
            }
            // Choosing Retirement Application
            else if (choice == 2) {
                workOrder = new RetirementApplicationWorkOrder("Retirement Request", user);
                WorkOrder SSACheckWorkOrder = new RetirementApplicationSSAWorkOrder("Retirement Request SSA Check", user);
                workOrder.Add(SSACheckWorkOrder);
                System.out.println();
                workOrder.Display(1);
                System.out.println("You choose to create Retirement Application Request. DMS is filling the required areas of the document with your informations automatically.\n");
                user.sendWorkOrder(workOrder);
                boolean check = WorkOrder.checkingAllDocuments(workOrder);
                if (check) {
                    System.out.println("Your application: " + workOrder.getName() + " is completely approved!");
                } else {
                    System.out.println("We are sorry for inform you that your application: " + workOrder.getName() + " has been rejected because of these documents:");
                    System.out.println("---------------------------------------------------");
                    for (Document document : WorkOrder.rejectedDocuments) {
                        System.out.println(document.getName());
                    }
                    System.out.println("---------------------------------------------------");

                }
                break;
            } else {
                System.out.println("Invalid choice.\nPress 1 for Vacation Application\nPress 2 for Retirement Application\nPress -1 to exit.");
                choice = input.nextInt();
            }

        }
    }

    //Main Method
    public static void main(String[] args) {
        DMS dms = DMS.getDMSInstance();
        dms.register();
        dms.createApplication();
    }

}

//This class is not related with the patterns which are developed in the project.
//This class is just creating departments and regarding documents needed in the related workorder,Also we store the signed documents in this central database class.
class Database {
    //Signed Documents will be stored here.
    public static ArrayList<Document> signedDocuments = new ArrayList<>();
    //Documents and Departments for related workorders will be created here.
    public static ArrayList<Document> VacationDocuments = new ArrayList<>();
    public static Stack<Department> HRVacationDepartments = new Stack<>();
    public static Stack<Department> AdministrationVacationDepartments = new Stack<>();
    public static ArrayList<Document> RetirementDocuments = new ArrayList<>();
    public static Stack<Department> PublicRelationRetirementDepartments = new Stack<>();
    public static Stack<Department> SSARetirementDepartments = new Stack<>();

    // Method for storing signed documents
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

    //Create HR Departments for Vacation Application
    public static Stack<Department> createHrDepartmentsForVacationApplication() {
        Department hr = new HumanResources("Human Resources", new Manager("Didem Tiknaz"));
        HRVacationDepartments.add(hr);
        return HRVacationDepartments;
    }

    //Create Administration Departments for Vacation Application
    public static Stack<Department> createAdministrationDepartmentsForVacationApplication() {
        Department softwareEngineeringDepartment = new EngineeringDepartment("Software Engineering Department", new Manager("Senem Kumova Metin"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery", new Manager("Yasar Guneri Sahin"));
        Department rectorate = new Rectorate("IUE Rectorate", new Manager("Murat Askar"));

        AdministrationVacationDepartments.add(softwareEngineeringDepartment);
        AdministrationVacationDepartments.add(engineeringDeanery);
        AdministrationVacationDepartments.add(rectorate);
        return AdministrationVacationDepartments;
    }

    // Create Documents for Vacaiton Application
    public static void createDocumentsForVacationApplication(User user) {
        System.out.println("For this application; you need to get these 3 documents signed.\n");
        System.out.println("""
                Vacation Permission From Administration Document
                Application Form
                Legal Working Day Document
                """);
        // Document Factory
        DocumentFactory documentFactory = DocumentFactory.getDocumentFactoryInstance();
        Document doc1 = documentFactory.createDocument("Vacation Permission from Administration Document", user.getAddress());
        Document doc2 = documentFactory.createDocument("Application Form", user.getAddress());
        Document doc3 = documentFactory.createDocument("Legal Working Day Document", user.getAddress());
        VacationDocuments.add(doc1);
        VacationDocuments.add(doc2);
        VacationDocuments.add(doc3);
    }

    // Parsing the documents for vacation application
    public static ArrayList<Document> dividingSpecificPartOfTheVacationDocumentsList(int firstIndex, int lastIndex) {
        ArrayList<Document> temp = new ArrayList<>();
        for (int i = firstIndex; i <= lastIndex; i++) {
            temp.add(VacationDocuments.get(i));
        }
        return temp;
    }

    // Create Public Relation Departments for EYT Application
    public static Stack<Department> createPublicRelationsDepartmentsForRetirementApplication() {
        Department publicRelations = new SSAPublicRelation("Public Relation Department", new Manager("Yasin Çolak"));
        PublicRelationRetirementDepartments.add(publicRelations);
        return PublicRelationRetirementDepartments;
    }

    // Create SSA Departments for EYT Application
    public static Stack<Department> createSSADepartmentsForRetirementApplication() {
        Department paymentDepartment = new SSARetirementPremiumPaymentControl("Premium Payment Department", new Manager("Hasan Yücetaş"));
        Department insuredDayControlDepartment = new SSARetirementInsuredDayControl("Insured Day Control Department", new Manager("Hande Baş"));

        SSARetirementDepartments.add(paymentDepartment);
        SSARetirementDepartments.add(insuredDayControlDepartment);
        return SSARetirementDepartments;
    }

    // Create Documents for EYT Application
    public static void createDocumentsForRetirementApplication(User user) {
        System.out.println("For this application; you need to get these 3 documents signed.\n");
        System.out.println("""
                Formal Age Document
                Formal Working Days Document
                Retirement Insurance Payment Document
                """);
        //Document Factory
        DocumentFactory documentFactory = DocumentFactory.getDocumentFactoryInstance();
        Document doc1 = documentFactory.createDocument("Formal Age Document", user.getAddress());
        Document doc2 = documentFactory.createDocument("Formal Working Days Document", user.getAddress());
        Document doc3 = documentFactory.createDocument("Retirement Insurance Payment Document", user.getAddress());
        RetirementDocuments.add(doc1);
        RetirementDocuments.add(doc2);
        RetirementDocuments.add(doc3);
    }


    // Parsing the documents for EYT application
    public static ArrayList<Document> dividingSpecificPartOfTheRetirementDocumentsList(int firstIndex, int lastIndex) {
        ArrayList<Document> temp = new ArrayList<>();
        for (int i = firstIndex; i <= lastIndex; i++) {
            temp.add(RetirementDocuments.get(i));
        }
        return temp;
    }
}


