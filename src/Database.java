import java.util.ArrayList;
import java.util.Stack;

public class Database {
    private ArrayList<Document> documents;

    public Database() {
        documents= new ArrayList<>();
    }

    public static Stack<Department> createDepartmentsForVacationApplication(){
        Stack<Department> departments = new Stack<Department>();
        Department softwareEngineeringDepartment= new EngineeringDepartment("Software Engineering Department",new Manager("Senem Kumova Metin",29,"Balcova"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery",new Manager("Yasar Guneri Sahin",45,"Balcova"));
        Department rectorate = new Rectorate("IUE Rectorate",new Manager("Murat Askar",70,"Balcova"));
        departments.add(softwareEngineeringDepartment);
        departments.add(engineeringDeanery);
        departments.add(rectorate);
        return departments;
    }

    public static Stack<Department> createDepartmentsForEYTApplication(){
        Stack<Department> departments = new Stack<Department>();
        Department softwareEngineeringDepartment= new EngineeringDepartment("Software Engineering Department",new Manager("Senem Kumova Metin",29,"Balcova"));
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery",new Manager("Yasar Guneri Sahin",45,"Balcova"));
        Department rectorate = new Rectorate("IUE Rectorate",new Manager("Murat Askar",70,"Balcova"));
        departments.add(softwareEngineeringDepartment);
        departments.add(engineeringDeanery);
        departments.add(rectorate);

        return departments;
    }

}
