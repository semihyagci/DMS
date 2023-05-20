public class Main {
    public static void main(String[] args) {
        System.out.println("***  Document Management System  ***\n");

        User user = new User("Semih", 19, "Buca");
        System.out.println("Hello " + user.getName() + " welcome to DMS...\n");

        DMS dms = DMS.getInstance();

        dms.createApplication(user);
    }
}
