import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        DMS dms= new DMS();
        User user= new User("Semih",19,"Buca");

        Document document= new Document("PDF","Vacation Application",user.getAddress());
        dms.createApplicationForVacation(user,document);
    }

}
