public class Manager extends People{
    public Manager(String name, int age, String address) {
        super(name, age, address);
    }

    public void signingByManager(Document document){
        System.out.println("Your application is signing by Manager right now.");
        document.setSignedByManager(true);
    }
}
