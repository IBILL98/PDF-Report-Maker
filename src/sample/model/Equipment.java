package sample.model;

public class Equipment {
    private String Name ;
    private int Id ;

    public Equipment() {
    }

    public Equipment(int id) {
        Id = id;
    }

    public Equipment(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Equipment(String name, int id) {
        Name = name;
        Id = id;
    }
}
