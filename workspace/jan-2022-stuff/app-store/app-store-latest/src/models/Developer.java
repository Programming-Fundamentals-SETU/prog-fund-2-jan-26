package models;

import java.util.Objects;

public class Developer {

    private String developerName; //developer name - max 30 chars
    private String developerWebsite; //website name - validate format
    private int numEmployees = 1;  // >= 1

    public Developer(String developerName, String developerWebsite) {
        this.developerName = developerName;
        this.developerWebsite = developerWebsite;
    }
    public Developer(String developerName, String developerWebsite, int numEmployees) {
        this.developerName = developerName;
        this.developerWebsite = developerWebsite;
        this.numEmployees = numEmployees;
    }

    public Developer(){
        //todo needed for serialization using XML encoder and decoder - maybe can remove if removing the XML encoder
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
    public int getNumEmployees() { return numEmployees;}
    public void setNumEmployees(int numEmployees)  {this.numEmployees = numEmployees;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(developerName, developer.developerName) && Objects.equals(developerWebsite, developer.developerWebsite);
    }

    @Override
    public String toString() {
        return  developerName + "(" + developerWebsite + ")";  //TODO more fields here
    }

}
