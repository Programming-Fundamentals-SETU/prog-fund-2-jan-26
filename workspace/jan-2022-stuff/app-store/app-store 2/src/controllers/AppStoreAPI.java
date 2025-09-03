package controllers;

import models.*;
import persistence.PersistenceManager;
import persistence.Serializer;
import utils.LicenseUtility;

import java.util.ArrayList;

public class AppStoreAPI implements PersistenceManager {

    private ArrayList<App> apps = new ArrayList<>();
    private Serializer serializer;

    public AppStoreAPI(Serializer serializer)  {
        this.serializer = serializer;
    }

    //---------------------
    // Create methods
    //---------------------

    public void addApp(App app) {
        apps.add(app);
    }

    //---------------------
    // Read methods
    //---------------------
    public String listAllApps(){
        String listAllApps = "";
        for (App app: apps){
            listAllApps += apps.indexOf(app) + ": " + app + "\n";
        }
        return listAllApps.equals("") ? "No apps" : listAllApps;
    }

    public String listAllGameApps(){
        String listAllApps = "";
        for (App app: apps){
            if (app instanceof GameApp) {
                listAllApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        return listAllApps.equals("") ? "No Game apps" : listAllApps;
    }

    public String listAllEducationApps(){
        String listAllApps = "";
        for (App app: apps){
            if (app instanceof EducationApp) {
                listAllApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        return listAllApps.equals("") ? "No Education apps" : listAllApps;
    }

    public String listAllProductivityApps(){
        String listAllApps = "";
        for (App app: apps){
            if (app instanceof ProductivityApp) {
                listAllApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        return listAllApps.equals("") ? "No Productivity apps" : listAllApps;
    }

    public String listAllAppsByLicenseType(String licenseType){
        String listAllApps = "";
        if (LicenseUtility.isValidLicense(licenseType)){
           for (App app: apps){
               if (app.getLicense().equalsIgnoreCase(licenseType)){
                  listAllApps += apps.indexOf(app) + ": " + app + "\n";
               }
           }
        }
        return listAllApps.equals("") ? "No apps for licence: " + licenseType : listAllApps;
    }


    public String listAllAppsAboveGivenStarRating(double rating){
        String listAllApps = "";
        if (rating > 0.0) {
            for (App app : apps) {
                if (app.calculateRating() >= rating) {
                    listAllApps += apps.indexOf(app) + ": " + app + "\n";
                }
            }
        }
        return listAllApps.equals("") ? "No apps have a rating of " + rating + " or above" : listAllApps;
    }

    public String listAllAppsBelowAgeRating(int rating){
        String listAllApps = "";
        for (App app : apps) {
            if (app.getAgeRating() <= rating) {
                listAllApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        return listAllApps.equals("") ? "No apps have an age rating of " + rating + " or lower" : listAllApps;
    }

    public String listAllAppsByCategory(String categoryType){
        String listAllApps = "";
        for (App app: apps){
            if (app.getCategory().equalsIgnoreCase(categoryType)){
                listAllApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        return listAllApps.equals("") ? "No apps for category: " + categoryType : listAllApps;
    }

    public String listAllRecommendedApps() {
        String recommended = "";
        for (App app: apps){
            if (app.isRecommendedApp()){
                recommended += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        return recommended.equals("") ? "No recommended apps" : recommended;
    }

    public String listAllAppsByChosenDeveloper(Developer developer) {
        String listAllApps = "";
        for (App app: apps){
            if (app.getDeveloper().equals(developer)){
                listAllApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        return listAllApps.equals("") ? "No apps for developer: " + developer : listAllApps;
    }

    //todo SORT by age rating ascending
    //todo SORT by rating descending
    //todo SORT by numberOfDownloads descending
    //todo top 5 paid apps (by number of downloads)
    //todo top 5 free apps (by number of downloads)(merge with previous one...use parameter flag to determine paid/free)

    //---------------------
    // Update methods
    //---------------------



    //---------------------
    // Delete methods
    //---------------------



    //---------------------
    // Validation methods
    //---------------------
    public boolean isValidAppName(String appName){
        if (appName.equals("")){
            return false;
        }
        else {
            for (App app : apps) {
                if (app.getAppName().equalsIgnoreCase(appName)) {
                    return false;
                }
            }
            return true;
        }
    }

    //---------------------
    // Persistence methods
    //---------------------
    @SuppressWarnings("unchecked")
    @Override
    public void load() throws Exception {
        apps = (ArrayList<App>) serializer.read();
    }

    @Override
    public void store() throws Exception {
        serializer.write(apps);
    }
}
