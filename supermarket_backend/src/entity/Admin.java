package entity;

public class Admin {

    private int id;
    private String name;
    private String email;
    private String username;

    private String password;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void getId(int id) {
         this.id = id;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(int id ,String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }


}