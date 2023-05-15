public class Manager extends People{
    public Manager(String name, int age, String address) {
        super(name, age, address);
    }

    public void signingByManager(Document document){
        document.setSignedByManager(true);
    }
}
