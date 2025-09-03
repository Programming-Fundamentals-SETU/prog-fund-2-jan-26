package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.*;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;
import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI {
    private List<App> apps = new ArrayList<>();

    //---------------------
    // Create methods
    //---------------------
    public void addApp(App app) {
        apps.add(app);
    }

    //---------------------
    // Listing methods
    //---------------------
    public String listAllApps(){
        String listAllApps = "";
        for (App app: apps){
            listAllApps += apps.indexOf(app) + ": " + app + "\n";
        }
        return listAllApps.equals("") ? "No apps" : listAllApps;
    }

    public String listSummaryOfAllApps(){
        String listAllApps = "";
        for (App app: apps){
            listAllApps += apps.indexOf(app) + ": " + app.appSummary() + "\n";
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

    public String listAllAppsByName(String name){
        String listAllApps = "";
            for (App app: apps){
                if (app.getAppName().toLowerCase().contains(name.toLowerCase())){
                    listAllApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        return listAllApps.equals("") ? "No apps for name: " + name : listAllApps;
    }

    public String listAllAppsAboveOrEqualAGivenStarRating(int rating){
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

    public int numberOfAppsByChosenDeveloper(Developer developer) {
        int numberOfApps = 0;
        for (App app: apps){
            if (app.getDeveloper().equals(developer)){
                numberOfApps++;
            }
        }
        return numberOfApps;
    }

    //todo top 5 paid apps (by number of downloads)
    //todo top 5 free apps (by number of downloads)(merge with previous one...use parameter flag to determine paid/free)

    //---------------------
    // Update methods
    //---------------------
    //todo here we have no methods as we use the mutators in Driver to update individual fields...maybe rethink this?
    // should we have the update happening in here instead?  Dunno?

    //---------------------
    // Delete methods
    //---------------------

    public App deleteAppByName(String appName){
        App app = getAppByName(appName);
        if ((appName != null)){
            apps.remove(app);
            return app;
        }
        return null;
    }

    public App deleteAppByIndex(int index){
        if (Utilities.isValidIndex(apps,index)){
            return apps.remove(index);
        }
        return null;
    }

    //---------------------
    // Reporting methods
    //---------------------
    public App randomApp(){
        int randomIndex = (int) (random() * (numberOfApps()-1));
        return getAppByIndex(randomIndex);
    }

    public void simulateAppDownloads(){
        for (App app :apps) {
            app.setNumberOfDownloads((int) (random() * 1000));
        }
    }

    public void simulateRatings(){
        for (App app :apps) {
            app.addRating(generateRandomRating());
        }
    }
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
                    return true;  //app name exists
                }
            }
            return false; //app name not found
        }
    }

    public App getAppByName(String appName){
        if (isValidAppName(appName)){
            for (App app : apps) {
                if (app.getAppName().equalsIgnoreCase(appName)) {
                    return app;
                }
            }
        }
        return null;
    }

    public App getAppByIndex(int index){
        return (isValidIndex(index)) ? apps.get(index) : null;
    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());
    }

    public int numberOfApps(){
        return apps.size();
    }

    //---------------------
    // Sorting methods
    //---------------------
    public void sortAppsByNameAscending()
    {
        for (int i = apps.size() -1; i >= 0; i--)
        {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }
            swapProducts(apps, i, highestIndex);
        }
    }

    public void sortAppsByAgeRatingAscending()
    {
        for (int i = apps.size() -1; i >= 0; i--)
        {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (apps.get(j).getAgeRating() > apps.get(highestIndex).getAgeRating()) {
                    highestIndex = j;
                }
            }
            swapProducts(apps, i, highestIndex);
        }
    }

    public void sortAppsByNumberOfDownloadsDescending(){
        for (int i = apps.size() -1; i >= 0; i--)
        {
            int lowestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (apps.get(j).getNumberOfDownloads() < apps.get(lowestIndex).getNumberOfDownloads()) {
                    lowestIndex = j;
                }
            }
            swapProducts(apps, i, lowestIndex);
        }
    }

    private void swapProducts(List<App> apps, int i, int j) {
        App smaller = apps.get(i);
        App bigger = apps.get(j);

        apps.set(i,bigger);
        apps.set(j,smaller);
    }

    //---------------------
    // Persistence methods
    //---------------------
    /**
     * The load method uses the XStream component to read all the objects from the xml
     * file stored on the hard disk.  The read objects are loaded into the associated ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("apps.xml"));
        apps = (List<App>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the ArrayList
     * to the xml file stored on the hard disk.
     *
     * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("apps.xml"));
        out.writeObject(apps);
        out.close();
    }

    public String fileName(){
        return "apps.xml";
    }

}
