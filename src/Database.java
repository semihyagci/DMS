import java.util.ArrayList;
import java.util.Stack;

public class Database {
    public static ArrayList<Document> signedDocuments = new ArrayList<>();
    public static ArrayList<Document> getVacationDocuments = new ArrayList<>();
    public static Stack<Department> getVacationDepartments = new Stack<>();
    public static ArrayList<Document> getEYTDocuments = new ArrayList<>();
    public static Stack<Department> getEYTDepartments = new Stack<>();

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
        getVacationDepartments.add(hr);
        return getVacationDepartments;
    }

    public static Stack<Department> createAdministrationDepartmentsForVacationApplication() {

        Department softwareEngineeringDepartment = new EngineeringDepartment("Software Engineering Department", new Manager("Senem Kumova Metin", 29, "Balcova"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery", new Manager("Yasar Guneri Sahin", 45, "Balcova"));
        Department rectorate = new Rectorate("IUE Rectorate", new Manager("Murat Askar", 70, "Balcova"));

        getVacationDepartments.add(softwareEngineeringDepartment);
        getVacationDepartments.add(engineeringDeanery);
        getVacationDepartments.add(rectorate);
        return getVacationDepartments;
    }


    public static Stack<Department> createDepartmentsForEYTApplication() {
        Department softwareEngineeringDepartment = new EngineeringDepartment("Software Engineering Department", new Manager("Senem Kumova Metin", 29, "Balcova"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery", new Manager("Yasar Guneri Sahin", 45, "Balcova"));
        Department rectorate = new Rectorate("IUE Rectorate", new Manager("Murat Askar", 70, "Balcova"));
        getEYTDepartments.add(softwareEngineeringDepartment);
        getEYTDepartments.add(engineeringDeanery);
        getEYTDepartments.add(rectorate);
        return getEYTDepartments;
    }

    public static ArrayList<Document> createDocumentsForVacationApplication(User user) {
        System.out.println("For this application; you need to get signed 2 documents \n");
        DocumentFactory documentFactory = new DocumentFactory();
        Document doc1 = documentFactory.createDocument("Legal Working Day Document",user.getAddress());
        Document doc2 = documentFactory.createDocument("Deserved Vacation Day Document", user.getAddress());
        getVacationDocuments.add(doc1);
        getVacationDocuments.add(doc2);
        return getVacationDocuments;
    }

    public static ArrayList<Document> createDocumentsForEYTApplication(User user) {
        System.out.println("For this application; you need to get signed 3 documents\n");
        DocumentFactory documentFactory = new DocumentFactory();
        Document doc1 = documentFactory.createDocument("Insurance Document", user.getAddress());
        Document doc2 = documentFactory.createDocument("Legal Working Day Document", user.getAddress());
        Document doc3 = documentFactory.createDocument("Age Document", user.getAddress());
        getEYTDocuments.add(doc1);
        getEYTDocuments.add(doc2);
        getEYTDocuments.add(doc3);
        return getEYTDocuments;
    }

}
