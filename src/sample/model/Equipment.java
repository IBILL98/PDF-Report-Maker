package sample.model;

public class Equipment {
    public Equipment() {
    }

    private String Name ;

    public Equipment(String name) {
        Name = name;
    }

    private int Id ;
    private String privatePoleDistance;

    private String MPCarrierMedium ;
    private String MagTech;
    private String UVLightIntensity ;
    private String DistanceOfLight;

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

    public String getPrivatePoleDistance() {
        return privatePoleDistance;
    }

    public void setPrivatePoleDistance(String privatePoleDistance) {
        this.privatePoleDistance = privatePoleDistance;
    }

    public String getMPCarrierMedium() {
        return MPCarrierMedium;
    }

    public void setMPCarrierMedium(String MPCarrierMedium) {
        this.MPCarrierMedium = MPCarrierMedium;
    }

    public String getMagTech() {
        return MagTech;
    }

    public void setMagTech(String magTech) {
        MagTech = magTech;
    }

    public String getUVLightIntensity() {
        return UVLightIntensity;
    }

    public void setUVLightIntensity(String UVLightIntensity) {
        this.UVLightIntensity = UVLightIntensity;
    }

    public String getDistanceOfLight() {
        return DistanceOfLight;
    }

    public void setDistanceOfLight(String distanceOfLight) {
        DistanceOfLight = distanceOfLight;
    }

    public Equipment(int id) {
        Id = id;
    }

    public Equipment(String name, int id) {
        Name = name;
        Id = id;
    }
}
