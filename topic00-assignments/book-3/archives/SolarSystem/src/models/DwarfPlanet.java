package models;


// Third Level: Dwarf models.Planet
class DwarfPlanet extends Planet {

    private String surfaceComposition; // () → "rocky with frozen nitrogen" //max 100 chars

    public DwarfPlanet(String name, double mass, double diameter, PlanetarySystem planetarySystem, double averageTemperature, String surfaceType, boolean hasLiquidWater, String surfaceComposition) {
        super(name, mass, diameter, planetarySystem, averageTemperature, surfaceType, hasLiquidWater);
        this.surfaceComposition = surfaceComposition;
    }

    public String getSurfaceComposition() {
        return surfaceComposition;
    }

    public void setSurfaceComposition(String surfaceComposition) {
        this.surfaceComposition = surfaceComposition;
    }

    @Override
    public String displayInfo() {
        return null;
    }
    @Override
    public String classifyBody() {
        return "Dwarf Planet";
    }
    @Override
    public String toString() {
        return "DwarfPlanet{ " + surfaceComposition + super.toString() + " }";
    }


}
