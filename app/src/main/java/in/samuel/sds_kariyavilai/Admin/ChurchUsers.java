package in.samuel.sds_kariyavilai.Admin;

public class ChurchUsers {
    private String id,name;
public  ChurchUsers(){
}
    public ChurchUsers(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
