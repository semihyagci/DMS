import java.util.ArrayList;
import java.util.Stack;

public class Database {
    public static ArrayList<Document> signedDocuments = new ArrayList<>();
    public static ArrayList<Document> HRVacationDocuments = new ArrayList<>();
    public static ArrayList<Document> AdministrationVacationDocuments = new ArrayList<>();
    public static Stack<Department> HRVacationDepartments = new Stack<>();
    public static Stack<Department> AdministrationVacationDepartments = new Stack<>();
    public static ArrayList<Document> EYTDocuments = new ArrayList<>();
    public static ArrayList<Document> VacationDocuments = new ArrayList<>();
    public static Stack<Department> EYTDepartments = new Stack<>();

    public Database() {
    }

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
