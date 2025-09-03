package models;

import java.util.ArrayList;

public abstract class App {

    private Developer developer; //the provider has to be registered to add an app.  then can search by them etc.
    private String appName; //must be unique in the system (regardless of case).
    private double appSize;  //measured in MB
    private double appVersion; //version number in format xx.xx
    private int ageRating; //min age for app
    private boolean inAppPurchase;
    private String language; //must be a valid language (see Language class)
    private String license; //must be a valid license (see License class)
    private double appCost; //zero or more
    private int numberOfDownloads = 0; //downloads per app - handled programatically, starts at zero
    private ArrayList<Rating> ratings = new ArrayList<>();  //contains all ratings...overall rating calculated from arraylist

    public App(Developer developer, String appName, double appSize, double appVersion, int ageRating, boolean inAppPurchase, String language, String license, double appCost) {
        this.developer = developer;
        this.appName = appName;
        this.appSize = appSize;
        this.appVersion = appVersion;
        this.ageRating = ageRating;
        this.inAppPurchase = inAppPurchase;
        this.language = language;
        this.license = license;
        this.appCost = appCost;
    }

    public abstract String getCategory();
    public abstract void setCategory(String category);
    public abstract boolean isRecommendedApp();


    //------------------------------
    // ratings methods
    //------------------------------

    public boolean addRating(Rating rating){
        return ratings.add(rating);
    }

    public String listRatings(){
        String listAllRatings = "";
        for (Rating rating: ratings){
            listAllRatings += rating + "\n";
        }
        return listAllRatings.equals("") ? "No ratings added yet" : listAllRatings;
    }

    public double calculateRating(){
        //rating is calculated as an average of the ratings added...note a zero value in ratingStars is ignored.
        int totalStars = 0;
        int totalNumOfRatings = 0;
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
        this.appSize = appSize;
    }

    public double getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(double appVersion) {
        this.appVersion = appVersion;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public boolean isInAppPurchase() {
        return inAppPurchase;
    }

    public void setInAppPurchase(boolean inAppPurchase) {
        this.inAppPurchase = inAppPurchase;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public double getAppCost() {
        return appCost;
    }

    public void setAppCost(double appCost) {
        this.appCost = appCost;
    }

    public int getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "App{" +
                "developer=" + developer +
                ", appName=" + appName +
                ", appSize=" + appSize +
                ", appVersion=" + appVersion +
                ", ageRating=" + ageRating +
                ", inAppPurchase=" + inAppPurchase +
                ", language='" + language + '\'' +
                ", license='" + license + '\'' +
                ", appCost=" + appCost +
                ", numberOfDownloads=" + numberOfDownloads +
                ", ratings=" + listRatings() +
                '}';
    }
}


