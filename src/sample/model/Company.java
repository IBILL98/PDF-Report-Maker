package sample.model;

public class Company {
    private String Name;
    private String Place;

    public Company(String name) {
        Name = name;
    }

    public Company(String name, String place) {
        Name = name;
        Place = place;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }


}
