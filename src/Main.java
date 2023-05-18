import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("***Document Management System***");
        DMS dms = new DMS();
        User user = new User("Semih", 19, "Buca");
        User user1 = new User("Ozan",22,"Foca");

        DocumentFactory documentFactory = new DocumentFactory();

        /*Document document = documentFactory.createDocument("Word","Vocation Application", user.getAddress());
        Document document1 = documentFactory.createDocument("PDF","Vocation Application PDF", user.getAddress());
        Document document2 = documentFactory.createDocument("CSV","TryDcoument","uasdfds");*/

        Document doc1= documentFactory.createDocument("Vacation Application", user.getAddress());
        Document doc2= documentFactory.createDocument("Vacation Application Doc2", user.getAddress());

        ArrayList<Document> docs= new ArrayList<>();
        docs.add(doc1);
        docs.add(doc2);

        dms.createApplicationForVacation(user, docs);
        dms.createApplicationForVacation(user1,docs);

    }
}
