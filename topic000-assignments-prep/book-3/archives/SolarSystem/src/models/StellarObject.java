package models;


import utils.SpectralTypeUtility;
import utils.Utilities;

// Second Level: Stellar Object
abstract class StellarObject extends CelestialBody {
    private char spectralType = 'M'; //must be one of OBAFGKM  default to M
    private double luminosity = 1000.0; //must be a value between 1000 and 200000 default to 1000.0

    public StellarObject(String name, double mass, double diameter, PlanetarySystem planetarySystem, char spectralType, double luminosity) {
        super(name, mass, diameter, planetarySystem);
        setSpectralType(spectralType);
        setLuminosity( luminosity);
    }

    public char getSpectralType() {
        return spectralType;
    }

    public void setSpectralType(char spectralType) {
        if(isValidStellartype(spectralType))
            this.spectralType = spectralType;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        if (Utilities.validRange(luminosity,1000, 200000 ))
            this.luminosity = luminosity;
    }

    private boolean isValidStellartype(char type){
            return SpectralTypeUtility.isValidSpectralType(type);
        }

    @Override
    public String displayInfo() {
        return "Spectral Type: "+ spectralType + " - Luminosity: " + luminosity;
    }
    @Override
    public String toString() {
        return "StellarObject:    " +
                "SpectralType: " + spectralType +
                ", luminosity: " + luminosity +
                 super.toString();
    }
}

/*
More Info
https://lweb.cfa.harvard.edu/~pberlind/atlas/htmls/note.html - spectral type
One fundamental property of a star is the total amount of energy it radiates each second. This energy output is called luminosity or absolute brightness.
https://www.teachastronomy.com/textbook/Properties-of-Stars/Stellar-Luminosity/#:~:text=A%20slightly%20modified%20version%20of,called%20luminosity%20or%20absolute%20brightness.

 */