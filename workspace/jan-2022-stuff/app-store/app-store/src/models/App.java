package models;

import utils.LicenseUtility;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public abstract class App {

    private Developer developer; //the provider has to be registered to add an app (handled in the Driver)
    private String appName = "No app name"; //must be unique in the system (regardless of case - handled in Driver).  Default value "No app name"
    private double appSize = 0;  //measured in MB - range from 1 to 1000 inclusive.  Default value 0
    private double appVersion = 1.0; //version number >= 1.0.  Default value 1.0.
    private int ageRating = 0; //min age for app - range from 0 to 18 inclusive.  Default value 0
    private boolean inAppPurchase = false; //no validation. default value false
    private String license = LicenseUtility.getDefaultLicense(); //must be a valid license (see LicenseUtility class)
    private double appCost = 0; //zero or more...no upper limit.  default value 0
    private int numberOfDownloads = 0; //downloads per app - handled programatically, starts at zero
    private List<Rating> ratings = new ArrayList<>();  //contains all ratings...overall rating calculated from arraylist

    public App(Developer developer, String appName, double appSize, double appVersion, int ageRating, boolean inAppPurchase, String license, double appCost) {
        setDeveloper(developer);
        setAppName(appName);
        setAppSize(appSize);
        setAppVersion(appVersion);
        setAgeRating(ageRating);
        setInAppPurchase(inAppPurchase);
        setLicense(license);
        setAppCost(appCost);
    }

    public abstract String getCategory();
    public abstract void setCategory(String category);
    public abstract boolean isRecommendedApp();

    //------------------------------
    // general methods
    //------------------------------
    public String appSummary(){
        return  appName + "(V" + appVersion + "), by " + developer + ", €" + appCost +
                    ". Downloads: " + numberOfDownloads + ". Rating: " + calculateRating();
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

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        if (Utilities.validRange(ageRating, 0, 18)) {
            this.ageRating = ageRating;
        }
    }

    public boolean isInAppPurchase() {
        return inAppPurchase;
    }

    public void setInAppPurchase(boolean inAppPurchase) {
        this.inAppPurchase = inAppPurchase;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        if (LicenseUtility.isValidLicense(license)) {
            this.license = license;
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

    public int getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(int numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return  appName
                + "(Version " + appVersion + ")" +
                ", By " + developer +
                ".\n\t Age Rating " + ageRating +
                ", Size: " + appSize + "MB" +
                ", In App Purchase: " + Utilities.booleanToYN(inAppPurchase) +
                ", License: " + license +
                ", Cost: " + appCost +
                ", Downloads: " + numberOfDownloads;
    }
}


