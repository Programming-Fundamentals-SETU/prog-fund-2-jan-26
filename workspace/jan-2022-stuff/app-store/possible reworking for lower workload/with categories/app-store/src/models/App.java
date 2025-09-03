package models;

import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public abstract class App {

    private Developer developer; //the provider has to be registered to add an app (handled in the Driver)
    private String appName = "No app name"; //must be unique in the system (regardless of case - handled in Driver).  Default value "No app name"
    private double appSize = 0;  //measured in MB - range from 1 to 1000 inclusive.  Default value 0
    private double appVersion = 1.0; //version number >= 1.0.  Default value 1.0.
    private double appCost = 0; //zero or more...no upper limit.  default value 0
    private List<Rating> ratings = new ArrayList<>();  //contains all ratings...overall rating calculated from arraylist

    public App(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        setDeveloper(developer);
        setAppName(appName);
        setAppSize(appSize);
        setAppVersion(appVersion);
        setAppCost(appCost);
    }

    public abstract String getCategory();
    public abstract void setCategory(String category);
    public abstract boolean isRecommendedApp();

    //------------------------------
    // general methods
    //------------------------------
    public String appSummary(){
        return  appName + "(V" + appVersion + "), by " + developer + ", €" + appCost
                + ". Rating: " + calculateRating();
    }

    //------------------------------
    // ratings methods
    //------------------------------

    public boolean addRating(Rating rating){
        return ratings.add(rating);
    }

    public String listRatings(){
        String listAllRatings = "";
        for (Rating rating: ratings){
            listAllRatings += "\t\t" + rating + "\n";
        }
        return listAllRatings.equals("") ? "\t\tNo ratings added yet" : listAllRatings;
    }

    public double calculateRating(){
        //rating is calculated as an average of the ratings added...note a zero value in ratingStars is ignored.
        double totalStars = 0;
        double totalNumOfRatings = 0;
        if (ratings.size() > 0) {
            for (Rating rating : ratings) {
                if (rating.getNumberOfStars() != 0){
                    totalStars += rating.getNumberOfStars();
                    totalNumOfRatings++;
                }
            }
            if (totalNumOfRatings != 0){
                return totalStars/totalNumOfRatings;
            }
        }
        return 0.0;
    }

    //------------------------------
    // getters, setters and toString
    //------------------------------

    public Developer getDeveloper() {
        return developer;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public double getAppSize() {
        return appSize;
    }

    public void setAppSize(double appSize) {
        if (Utilities.validRange(appSize, 1, 1000)) {
            this.appSize = appSize;
        }
    }

    public double getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(double appVersion) {
        if(Utilities.greaterThanOrEqualTo(appVersion, 1.0)){
            this.appVersion = appVersion;
        }
    }

    public double getAppCost() {
        return appCost;
    }

    public void setAppCost(double appCost) {
        if(Utilities.greaterThanOrEqualTo(appCost, 0.0)) {
            this.appCost = appCost;
        }
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return  appName
                + "(Version " + appVersion + ")" +
                ", By " + developer +
                ", Size: " + appSize + "MB" +
                ", Cost: " + appCost;
    }
}


