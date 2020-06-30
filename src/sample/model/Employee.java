package sample.model;

import java.sql.Date;
import java.time.LocalDate;

public class Employee {
    private String Name;
    private String LastName;
    private String Username;
    private int Level;
    private String Password;
    private int Id;
    private String Work;
    private LocalDate Cdate;


    public LocalDate getCdate() {
        return Cdate;
    }

    public void setCdate(LocalDate cdate) {
        Cdate = cdate;
    }

    public Employee(String name, String lastName, String username, int level, String password, String work, LocalDate cdate) {
        Name = name;
        LastName = lastName;
        Username = username;
        Level = level;
        Password = password;
        Work = work;
        Cdate = cdate;
    }

    public Employee() {
    }

    public Employee(String username) {
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

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        this.Work = work;
    }
    

}
