package sample.model;

public class Employee {
    private String Name;
    private String LastName;
    private String Username;
    private String Level;
    private String Password;


    private String work;

    public Employee(String name, String lastName, String username, String level, String password, String work) {
        Name = name;
        LastName = lastName;
        Username = username;
        Level = level;
        Password = password;
        this.work = work;
    }

    public Employee() {
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

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
