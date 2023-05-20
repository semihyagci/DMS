import java.util.ArrayList;
import java.util.Stack;

public class Database {
    public static ArrayList<Document> signedDocuments = new ArrayList<>();

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

    public static Stack<Department> createDepartmentsForVacationApplication() {
        Stack<Department> departments = new Stack<Department>();
        Department softwareEngineeringDepartment = new EngineeringDepartment("Software Engineering Department", new Manager("Senem Kumova Metin", 29, "Balcova"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery", new Manager("Yasar Guneri Sahin", 45, "Balcova"));
        Department rectorate = new Rectorate("IUE Rectorate", new Manager("Murat Askar", 70, "Balcova"));
        departments.add(softwareEngineeringDepartment);
        departments.add(engineeringDeanery);
        departments.add(rectorate);
        return departments;
    }

    public static Stack<Department> createDepartmentsForEYTApplication() {
        Stack<Department> departments = new Stack<Department>();
        Department softwareEngineeringDepartment = new EngineeringDepartment("Software Engineering Department", new Manager("Senem Kumova Metin", 29, "Balcova"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery", new Manager("Yasar Guneri Sahin", 45, "Balcova"));
        Department rectorate = new Rectorate("IUE Rectorate", new Manager("Murat Askar", 70, "Balcova"));
        departments.add(softwareEngineeringDepartment);
        departments.add(engineeringDeanery);
        departments.add(rectorate);

        return departments;
    }

    public static ArrayList<Document> createDocumentsForVacationApplication(User user) {
        System.out.println("For this application; you need to get signed 2 documents \n");
        DocumentFactory documentFactory = new DocumentFactory();
        Document doc1 = documentFactory.createDocument("Legal Working Day Document",user.getAddress());
        Document doc2 = documentFactory.createDocument("Deserved Vacation Day Document", user.getAddress());
        ArrayList<Document> docs = new ArrayList<>();
        docs.add(doc1);
        docs.add(doc2);
        return docs;
    }

    public static ArrayList<Document> createDocumentsForEYTApplication(User user) {
        System.out.println("For this application; you need to get signed 3 documents\n");
        DocumentFactory documentFactory = new DocumentFactory();
        Document doc1 = documentFactory.createDocument("Insurance Document", user.getAddress());
        Document doc2 = documentFactory.createDocument("Legal Working Day Document", user.getAddress());
        Document doc3 = documentFactory.createDocument("Age Document", user.getAddress());
        ArrayList<Document> docs = new ArrayList<>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
        return docs;
    }

}
