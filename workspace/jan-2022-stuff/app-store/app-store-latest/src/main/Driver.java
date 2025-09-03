package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import models.App;
import models.Developer;
import models.EducationApp;
import persistence.JSONSerializer;
import persistence.XMLSerializer;
import utils.ErrorCodes;
import utils.LicenseUtility;
import utils.ScannerInput;
import utils.ValidationUtility;

import java.io.File;

public class Driver {

    private DeveloperAPI developerAPI;
    private AppStoreAPI appStoreAPI;

    public static void main(String[] args) {
        new Driver().start();
    }

    public void start() {
        developerAPI = new DeveloperAPI(new JSONSerializer(new File("developers.json")));
        appStoreAPI = new AppStoreAPI(new XMLSerializer(new File("app-store.xml")));
        loadAllData();  //load all data once the serializers are set up
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println(" ----------App Store---------");
        System.out.println("|  1) Developer CRUD MENU    |");
        System.out.println("|  2) App Store CRUD MENU    |");
        System.out.println("|  3) Reports MENU           |");
        System.out.println("|----------------------------|");
        System.out.println("|  4) Search Developers      |");
        System.out.println("|  5) Search Apps            |");  //todo by different criteria i.e. look at the list methods and give options based on that.
        System.out.println("|  6) Sort Apps              |");  //todo (and give a list of options - not a recurring menu thou)
        System.out.println("|  7) Recommended Apps       |");
        System.out.println("|  8) Random App of the Day  |");  //todo
        System.out.println("|  9) Simulate app downloads |");  //todo simulate app downloads for random apps (to give data for recommended apps and reports etc).
        System.out.println("|  10) Simulate ratings      |");  //todo simulate ratings for random apps (to give data for recommended apps and reports etc).
        System.out.println("|----------------------------|");
        System.out.println("|  20) Save all              |");
        System.out.println("|  21) Load all              |");
        System.out.println("|----------------------------|");
        System.out.println("|  0) Exit                   |");
        System.out.println(" ---------------------------- ");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1:  runDeveloperMenu(); break;
                case 2:  runAppStoreMenu(); break;
                case 3:  runReportsMenu(); break;
                case 4:  listAppsByDeveloper(getDeveloperByName()); break;
                case 5:  break;
                case 6:  break;
                case 7:  System.out.println(appStoreAPI.listAllRecommendedApps()); break;
                case 8:  break;
                case 9:  break;
                case 10:  break;
                case 20: saveAllData();  break;
                case 21: loadAllData(); break;
                default: System.out.println("Invalid option entered: " + option); break;
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private void exitApp(){
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }

    //----------------------
    //  Developer Menu Items
    //----------------------
    private int developerMenu() {
        System.out.println("""
                 -------Developer Menu-------
                |   1) Add a developer       |
                |   2) List all developers   |
                |   3) Update developer      |
                |   4) Delete developer      |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runDeveloperMenu() {
        int option = developerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addDeveloper();
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3 -> updateDeveloper();
                case 4 -> deleteDeveloper();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = developerMenu();
        }
    }

    private void addDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        String developerWebsite = ScannerInput.validNextLine("Please enter the developer website: ");

        if (developerAPI.addDeveloper(new Developer(developerName, developerWebsite))){
            System.out.println("Add successful");
        }
        else{
            System.out.println("Add not successful");
        }
    }

    private void updateDeveloper(){
        System.out.println(developerAPI.listDevelopers());
        Developer developer = getDeveloperByName();
        if (developer != null){
            String developerWebsite = ScannerInput.validNextLine("Please enter new website: ");
            if (developerAPI.updateDeveloperWebsite(developer.getDeveloperName(), developerWebsite))
                System.out.println("Developer Website Updated");
            else
                System.out.println("Developer Website NOT Updated");
        }
        else
            System.out.println("Developer name is NOT valid");
    }

    private void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null){
            System.out.println("Delete successful");
        }
        else{
            System.out.println("Delete not successful");
        }
    }

    //---------------------
    //  App Store Menu
    //---------------------

    private int appStoreMenu() {
        System.out.println("""
                 -------App Store Menu-------
                |   1) Add an app            |
                |   2) List apps             |  
                |   3) Update app            |  
                |   4) Delete app            |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runAppStoreMenu() {
        int option = appStoreMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addApp();
                case 2 -> listApps();
                case 3 -> updateApp();
                case 4 -> deleteApp();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = appStoreMenu();
        }
    }

    private void addApp(){
        System.out.println(developerAPI.listDevelopers());
        Developer developer = getDeveloperByName();
        if (developer != null){
            String appName = getValidAppName();
            if (!appName.equals("")){
            double appSize = ScannerInput.validNextDouble("\tApp Size (in MB): ");
            double appVersion = ScannerInput.validNextDouble("\tApp Version: ");
            int ageRating = ScannerInput.validNextInt("\tAge Rating: ");
            boolean inAppPurchase = ValidationUtility.YNToBoolean(
                                        ScannerInput.validNextChar("\tIn app purchase (Y/N): "));
            String license = getValidLicense();
            double appCost = ScannerInput.validNextDouble("\tApp cost: ");

             //todo for other types (and do category and level)
            appStoreAPI.addApp(new EducationApp(developer, appName, appSize, appVersion, ageRating, inAppPurchase, license, appCost, "", 0));
            }
            else{
                System.out.println("App name already exists / App name is not valid");
            }
        }

        else{
            System.out.println("Developer name is NOT valid");
        }
    }

    private void listApps(){
        System.out.println("""
                    Which type of app do you want to list?
                      1. All Apps
                      2. Games
                      3. Education
                      4. Productivity""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option){
            case 1 -> System.out.println("All Apps\n------------\n" + appStoreAPI.listAllApps());
            case 2 -> System.out.println("Games Apps\n------------\n" + appStoreAPI.listAllGameApps());
            case 3 -> System.out.println("Education Apps\n------------\n" + appStoreAPI.listAllEducationApps());
            case 4 -> System.out.println("Productivity Apps\n------------\n" + appStoreAPI.listAllProductivityApps());
        }
    }

    private void updateApp(){
        System.out.println(appStoreAPI.listSummaryOfAllApps());
        String appName = ScannerInput.validNextLine("Enter the name of the app you would you like to update: ");
        if (appStoreAPI.isValidAppName(appName)){
            App app = appStoreAPI.getAppByName(appName);
            //todo add options from sub class????
            System.out.println("""
                    Which Fields do you want to update?
                      1. Developer
                      2. App Size
                      3. App Cost""");
            int option = ScannerInput.validNextInt("==>> ");
            switch (option){
                case 1 -> app.setDeveloper(getDeveloperByName());
                case 2 -> app.setAppSize(ScannerInput.validNextDouble("\tApp Size (in MB): "));
                case 3 -> app.setAppCost(ScannerInput.validNextDouble("\tApp Cost: "));
            }
        }
        else{
            System.out.println("App name is NOT valid");
        }
    }

    private void deleteApp(){
        System.out.println(appStoreAPI.listSummaryOfAllApps());
        int appIndex = ScannerInput.validNextInt("Please enter the app index: ");
        if (appStoreAPI.deleteAppByIndex(appIndex) != null){
            System.out.println("Delete successful");
        }
        else{
            System.out.println("Delete not successful");
        }
    }

    //----------------------
    //  Reports Menu Items
    //----------------------
    private int reportsMenu() {
        System.out.println(" --------Reports Menu--------");
        System.out.println("|   1) Apps Overview         |");
        System.out.println("|   2) Developers Overview   |");
        System.out.println("|   0) RETURN to main menu   |");
        System.out.println(" ----------------------------");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runReportsMenu() {
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1:  appStoreOverview(); break;
                case 2:  developerOverview(); break;
                default: System.out.println("Invalid option entered" + option); break;
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
    }

    private void appStoreOverview() {
        System.out.println("""
                Which App Store Report do you want:
                  1) All Apps
                  2) Education Apps
                  3) Game Apps
                  4) Productivity Apps""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {
            case 1 -> System.out.println(appStoreAPI.listAllApps());
            case 2 -> System.out.println(appStoreAPI.listAllEducationApps());
            case 3 -> System.out.println(appStoreAPI.listAllGameApps());
            case 4 -> System.out.println(appStoreAPI.listAllProductivityApps());
            default -> System.out.println("Invalid option");
        }
    }

    private void developerOverview() {
        int option = ScannerInput.validNextInt("View developer report on:  " +
                "\n\t1) All Developers" +
                "\n\t2) Specific Developer " +
                "\n==>> ");

        switch (option) {
            case 1 -> {
                for (Developer developer : developerAPI.getDevelopers()){
                    listAppsByDeveloper(developer);
                }
            }
            case 2 -> listAppsByDeveloper(getDeveloperByName());
            default -> System.out.println("Invalid option");
        }
    }


    //---------------------
    //  "Get" Helper Methods
    //---------------------
    private String getValidLicense() {
        String license = ScannerInput.validNextLine("\tLicense options " + LicenseUtility.getLicenseKeys() + ": ");
        if (LicenseUtility.isValidLicense(license)) {
            return license;
        } else {
            System.err.println("\tLicense is not valid - setting default of: " + LicenseUtility.getDefaultLicense());
            return LicenseUtility.getDefaultLicense();
        }
    }

    private String getValidAppName(){
        String appName = ScannerInput.validNextLine("\tApp Name (must be unique): ");
        if (appStoreAPI.isValidAppName(appName)) {
            return appName;
        } else {
            System.err.println("\tApp name already exists / is not valid.");
            return "";
        }
    }

    private Developer getDeveloperByName(){
        String developerName = ScannerInput.validNextLine("Please enter the developer's name: ");
        if (developerAPI.isValidDeveloper(developerName)){
            return developerAPI.getDeveloperByName(developerName);
        }
        else{
            return null;
        }
    }

    //---------------------
    //  "List" Helper Methods
    //---------------------

    private void listAppsByDeveloper(Developer developer){
        if (developer != null){
            System.out.println(developer);
            int numberOfApps = appStoreAPI.numberOfAppsByChosenDeveloper(developer);
            System.out.println("Total number of Apps: " + numberOfApps);
            if (numberOfApps != 0) {
                System.out.println(appStoreAPI.listAllAppsByChosenDeveloper(developer));
            }
        }
        else{
            System.out.println("No developer exists");
        }
    }

    //---------------------
    //  Persistence Menu Items
    //---------------------

    private void saveAllData(){
        System.out.println("Storing all data....");
        try {
            developerAPI.store();
            appStoreAPI.store();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadAllData(){
        System.out.println("Loading all data....");
        try {
            developerAPI.load();
            appStoreAPI.load();
        } catch (Exception e) {
            System.err.println("Error loading from this file:  " + e);
        }
    }

}
