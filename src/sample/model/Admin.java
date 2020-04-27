package sample.model;

public class Admin {
    private String Name;
    private String LastName;
    private String Username;
    private String Password;

    public Admin() {
    }

    public Admin(String name, String lastName, String username, String password) {
        Name = name;
        LastName = lastName;
        Username = username;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
