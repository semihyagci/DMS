import java.util.ArrayList;
import java.util.Stack;

public class Database {
    public static ArrayList<Document> signedDocuments = new ArrayList<>();
    ;

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

}
