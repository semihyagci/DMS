import java.util.ArrayList;

public class DMS {
 public static void main(String[] args) {
    DMS dms= new DMS();
    User user= new User("Semih",19,"Buca");
    EngineeringDepartment department= new EngineeringDepartment("Software Engineering Department",new Manager("Senem Kumova Metin",29,"Balcova"));
    Document document= new Document("PDF","Vacation Application",user.getAddress());

    dms.createApplication(user,document,department);
 }
 public void createApplication(User user, Document document,Department department){
  ArrayList a= new ArrayList<Document>();
  a.add(document);
  document.Attach(user);
  user.sendWorkOrder(new WorkOrder("Vacation Request",a,department));
 }
}
