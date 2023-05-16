import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Department> departments = new Stack<Department>();
        DMS dms= new DMS(departments);
        User user= new User("Semih",19,"Buca");

        Department softwareEngineeringDepartment= new EngineeringDepartment("Software Engineering Department",new Manager("Senem Kumova Metin",29,"Balcova"),dms);
        Department engineeringDeanery = new EngineeringDeanery("Engineering Deanery",new Manager("Yasar Guneri Sahin",45,"Balcova"),dms);
        Department rectorate = new Rectorate("IUE Rectorate",new Manager("Murat Askar",70,"Balcova"),dms);
        departments.add(softwareEngineeringDepartment);
        departments.add(engineeringDeanery);
        departments.add(rectorate);

        Document document= new Document("PDF","Vacation Application",user.getAddress());
        dms.createApplication(user,document,departments);

    }

}
