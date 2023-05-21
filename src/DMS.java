import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

//Singleton-Facade
//This class as a work engine will route the
//work order from one department into another
public class DMS {
    private static DMS uniqueDMSInstance=null;
    private User user;
    private DMS(User user) {
        this.user=user;
        System.out.println("***  Document Management System  ***\n");
        System.out.println("Hello " + this.user.getName() + " welcome to DMS...\n");
    }

    //Singleton getInstance method.
    public static DMS getInstance(User user){
        if (uniqueDMSInstance==null)
            uniqueDMSInstance=new DMS(user);
        return uniqueDMSInstance;
    }

    public void createApplication(User user) {
        Scanner input = new Scanner(System.in);

        System.out.println("Press 1 for Vacation Application\nPress 2 for EYT Application\n");
        int choice=input.nextInt();

        while (true){
        if (choice==1){
            WorkOrder vacationApplicationWorkOrder = new VacationApplicationWorkOrder("Vacation Request",user);
            WorkOrder vacationHrCheckSubWorkOrder = new VacationApplicationHRWorkOrder("Vacation Check Request Human Resources",user);
            WorkOrder vacationAdministrationCheckSubWorkOrder = new VacationApplicationAdministrationWorkOrder("Vacation Check Request Administration",user);
            vacationApplicationWorkOrder.Add(vacationHrCheckSubWorkOrder);
            vacationApplicationWorkOrder.Add(vacationAdministrationCheckSubWorkOrder);
            System.out.println();
            vacationApplicationWorkOrder.Display(1);
            System.out.println();
        System.out.println("You choose to create Vacation Application Request. DMS is filling the required areas of the document with your informations automatically.\n");
        user.sendWorkOrder(vacationApplicationWorkOrder);
        break;
        }else if (choice==2){
            System.out.println("You choose to create EYT Application Request. DMS is filling the required areas of the document with your informations automatically.\n");
            user.sendWorkOrder(new EYTApplicationWorkOrder("EYT Request",user));
            break;
        }else {
            System.out.println("Invalid choice.\n Press 1 for Vacation Application\nPress 2 for EYT Application\n");
            choice=input.nextInt();
        }
        }
    }

    public static void main(String[] args) {
        User user = new User("Semih", 19, "Buca");
        DMS dms = DMS.getInstance(user);
        dms.createApplication(user);
    }

}

//This class is not related with the patterns which are developed in the project.
//This class is just creating departments and regarding documents needed in the related workorder. Also we store the signed documents in this central database class.
class Database {
    public static ArrayList<Document> signedDocuments = new ArrayList<>();
    public static ArrayList<Document> VacationDocuments = new ArrayList<>();
    public static Stack<Department> HRVacationDepartments = new Stack<>();
    public static Stack<Department> AdministrationVacationDepartments = new Stack<>();
    public static ArrayList<Document> EYTDocuments = new ArrayList<>();
    public static Stack<Department> EYTDepartments = new Stack<>();


    public static void storeSignedWordDocument(Document document) {
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
    public static Stack<Department> createHrDepartmentsForVacationApplication() {
        Department hr = new HumanResources("Human Resources", new Manager("Didem Tiknaz", 29, "Balcova"));
        HRVacationDepartments.add(hr);
        return HRVacationDepartments;
    }

    public static Stack<Department> createAdministrationDepartmentsForVacationApplication() {
        Department softwareEngineeringDepartment = new EngineeringDepartment("Software Engineering Department", new Manager("Senem Kumova Metin", 29, "Balcova"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery", new Manager("Yasar Guneri Sahin", 45, "Balcova"));
        Department rectorate = new Rectorate("IUE Rectorate", new Manager("Murat Askar", 70, "Balcova"));

        AdministrationVacationDepartments.add(softwareEngineeringDepartment);
        AdministrationVacationDepartments.add(engineeringDeanery);
        AdministrationVacationDepartments.add(rectorate);
        return AdministrationVacationDepartments;
    }


    public static Stack<Department> createDepartmentsForEYTApplication() {
        Department softwareEngineeringDepartment = new EngineeringDepartment("Software Engineering Department", new Manager("Senem Kumova Metin", 29, "Balcova"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery", new Manager("Yasar Guneri Sahin", 45, "Balcova"));
        Department rectorate = new Rectorate("IUE Rectorate", new Manager("Murat Askar", 70, "Balcova"));
        EYTDepartments.add(softwareEngineeringDepartment);
        EYTDepartments.add(engineeringDeanery);
        EYTDepartments.add(rectorate);
        return EYTDepartments;
    }


    public static void createDocumentsForVacationApplication(User user) {
        System.out.println("For this application; you need to get signed 3 documents \n");
        DocumentFactory documentFactory = new DocumentFactory();
        Document doc1 = documentFactory.createDocument("Vacation Permission From Administartion Document",user.getAddress());
        Document doc2 = documentFactory.createDocument("Application Form",user.getAddress());
        Document doc3 = documentFactory.createDocument("Legal Working Day Document", user.getAddress());
        VacationDocuments.add(doc1);
        VacationDocuments.add(doc2);
        VacationDocuments.add(doc3);
    }

    public static ArrayList<Document> createDocumentsForEYTApplication(User user) {
        System.out.println("For this application; you need to get signed 3 documents\n");
        DocumentFactory documentFactory = new DocumentFactory();
        Document doc1 = documentFactory.createDocument("Insurance Document", user.getAddress());
        Document doc2 = documentFactory.createDocument("Legal Working Day Document", user.getAddress());
        Document doc3 = documentFactory.createDocument("Age Document", user.getAddress());
        EYTDocuments.add(doc1);
        EYTDocuments.add(doc2);
        EYTDocuments.add(doc3);
        return EYTDocuments;
    }
    public static ArrayList<Document> dividingSpecificPartOfTheVacationDocumentsList(int firstIndex,int lastIndex){
        ArrayList<Document> temp=new ArrayList<>();
        for (int i=firstIndex;i<=lastIndex;i++){
            temp.add(VacationDocuments.get(i));
        }
        return temp;
    }
}

