package sample.model;

public class Company {
    private String Name;
    private String Place;
    private String OfferNo;
    private String JobOrderNo;
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Company() {
    }

    public Company(String name, String OfferNo, boolean b) {
        Name = name;
        this.OfferNo = OfferNo;
        b = true;
    }
    public Company(String name, String JobOrderNo, boolean b,boolean a) {
        Name = name;
        this.JobOrderNo = JobOrderNo;
        b = true;
        a = true;
    }


    public Company(String name, String Place) {
        Name = name;
        this.Place = Place;
    }

    public Company(String name) {
        Name = name;
    }

    public Company(String name, String place, String offerNo, String jobOrderNo) {
        Name = name;
        Place = place;
        OfferNo = offerNo;
        JobOrderNo = jobOrderNo;
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

    public String getOfferNo() {
        return OfferNo;
    }

    public void setOfferNo(String offerNo) {
        OfferNo = offerNo;
    }

    public String getJobOrderNo() {
        return JobOrderNo;
    }

    public void setJobOrderNo(String jobOrderNo) {
        JobOrderNo = jobOrderNo;
    }
}
