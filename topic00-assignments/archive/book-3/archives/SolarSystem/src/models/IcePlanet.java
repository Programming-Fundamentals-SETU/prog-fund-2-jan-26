package models;


// Third Level: Ice models.Planet
public class IcePlanet extends Planet {
    private String iceComposition;  //max 30 char

    public IcePlanet(String name, double mass, double diameter, PlanetarySystem planetarySystem, double averageTemperature, String surfaceType, boolean hasLiquidWater, String iceComposition) {
        super(name, mass, diameter, planetarySystem, averageTemperature, surfaceType, hasLiquidWater);
        this.iceComposition = iceComposition;
    }

    public String getIceComposition() {
        return iceComposition;
    }

    public void setIceComposition(String iceComposition) {
        this.iceComposition = iceComposition;
    }
    @Override
    public String classifyBody() {
        return "Ice Planet";
    }
    @Override
    public String toString() {
        return "IcePlanet{" +
                "iceComposition='" + iceComposition + '\'' +
                 '}';
    }

    @Override
    public String displayInfo() {
        return null;
    }
}