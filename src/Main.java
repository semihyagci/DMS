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

        dms.createApplicationForVacation(user, documentFactory.createDocument("Vocation Application", user.getAddress()));
        dms.createApplicationForVacation(user1,documentFactory.createDocument("Vocation Application 2", user.getAddress()));

    }
}
