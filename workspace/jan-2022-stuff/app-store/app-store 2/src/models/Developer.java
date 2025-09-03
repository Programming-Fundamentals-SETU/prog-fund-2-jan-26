package models;

public class Developer {

    private String developerName; //developer name - max 30 chars
    private String developerWebsite; //website name - validate format

    public Developer(String developerName, String developerWebsite) {
        this.developerName = developerName;
        this.developerWebsite = developerWebsite;
    }

    public Developer(){
        //needed for serialization using XML encoder and decoder - maybe can remove if removing the XML encoder
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperWebsite() {
        return developerWebsite;
    }

    public void setDeveloperWebsite(String developerWebsite) {
        this.developerWebsite = developerWebsite;
    }

    @Override
    public String toString() {
        return  developerName + "(" + developerWebsite + ")\n";
    }

}
