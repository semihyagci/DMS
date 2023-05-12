public class User {
    //Observer
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Update(Document document) {
        //Update fields in here to show user
    }

}
