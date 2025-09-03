package models;



import utils.CoreCompositionUtility;
import utils.Utilities;

public class GasPlanet extends Planet {
    private String gasComposition; //max 20 chars
    private String coreComposition= "UNKNOWN";// Must be one of Rocky and Metallic, Proportionally Small, Liquid Metallic Hydrogen
                                    //Compressed Hydrogen or Ice Giant default UNKNOWN
    private double radiationLevel = 0.9;// () → Higher in gas giant min .01 max 200.05 default .9

    public GasPlanet(String name, double mass, double diameter, PlanetarySystem planetarySystem, double averageTemperature, String surfaceType, boolean hasLiquidWater, String gasComposition, String coreComposition, double radiationLevel) {
        super(name, mass, diameter, planetarySystem, averageTemperature, surfaceType, hasLiquidWater);
        this.gasComposition = Utilities.truncateString(gasComposition, 20);
        setCoreComposition(coreComposition);
        setRadiationLevel(radiationLevel);
    }

    public String getGasComposition() {
        return gasComposition;
    }

    public void setGasComposition(String gasComposition) {
        if ( Utilities.validStringlength(gasComposition, 20) )
                this.gasComposition = gasComposition;
    }


    public String getCoreComposition() {
        return coreComposition;
    }

    public void setCoreComposition(String coreComposition) {
        if (CoreCompositionUtility.isValidCoreType(coreComposition))
            this.coreComposition = coreComposition;
    }

    public double getRadiationLevel() {
        return radiationLevel;
    }

    public void setRadiationLevel(double radiationLevel) {
        if (Utilities.validRange(radiationLevel,.01, 200.5, .01 ))
            this.radiationLevel = radiationLevel;
    }

    @Override
    public String toString() {
        return "GasPlanet: " +
                "gasComposition: " + gasComposition +
               ", coreComposition: " + coreComposition +
                ", radiationLevel: " + radiationLevel + super.toString() ;
    }
    @Override
    public String classifyBody() {
        return " Gas Planet";
    }
    @Override
    public String displayInfo() {
        return
        "gasComposition: " + gasComposition +
                ", coreComposition: " + coreComposition +
                ", radiationLevel: " + radiationLevel ;
    }
}
