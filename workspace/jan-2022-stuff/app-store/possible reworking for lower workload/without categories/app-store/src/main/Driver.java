package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import models.*;
import utils.ScannerInput;
import utils.Utilities;

public class Driver {

    private DeveloperAPI developerAPI = new DeveloperAPI();
    private AppStoreAPI appStoreAPI = new AppStoreAPI();

    public static void main(String[] args) {
        new Driver().start();
    }

    public void start() {
        loadAllData();
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("""
                 -------------App Store------------
                |  1) Developer - Management MENU  |
                |  2) App - Management MENU        |
                |  3) Reports MENU                 |
                |----------------------------------|
                |  4) Search                       |
                |  5) Sort                         |
                |----------------------------------|
                |  6) Recommended Apps             |
                |  7) Random App of the Day        |
                |  8) Simulate ratings             |
                |----------------------------------|
                |  20) Save all                    |
                |  21) Load all                    |
                |----------------------------------|
                |  0) Exit                         |
                 ----------------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runDeveloperMenu();
                case 2 -> runAppStoreMenu();
                case 3 -> runReportsMenu();
                case 4 -> searchAppsBySpecificCriteria();
                case 5 -> sortApps();
                case 6 -> System.out.println(appStoreAPI.listAllRecommendedApps());
                case 7 -> randomAppOfTheDay();
                case 8 -> simulateRatings();
                case 20 -> saveAllData();
                case 21 -> loadAllData();
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }

    //--------------------------------------------------
    //  Developer Management - Menu Items
    //--------------------------------------------------
    private int developerMenu() {
        System.out.println("""
                 -------Developer Menu-------
                |   1) Add a developer       |
                |   2) List developer        |
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

        if (developerAPI.addDeveloper(new Developer(developerName, developerWebsite))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void updateDeveloper() {
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            String developerWebsite = ScannerInput.validNextLine("Please enter new website: ");
            if (developerAPI.updateDeveloperWebsite(developer.getDeveloperName(), developerWebsite))
                System.out.println("Developer Website Updated");
            else
                System.out.println("Developer Website NOT Updated");
        } else
            System.out.println("Developer name is NOT valid");
    }

    private void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    //--------------------------------------------------
    //  App Management Menu
    //--------------------------------------------------
    private int appStoreMenu() {
        System.out.println("""
                 -------App Store Menu-------
                |   1) Add an app            |
                |   2) Update app            |
                |   3) Delete app            |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runAppStoreMenu() {
        int option = appStoreMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addApp();
                case 2 -> updateApp();
                case 3 -> deleteApp();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = appStoreMenu();
        }
    }

    private void addApp() {
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            String appName = ScannerInput.validNextLine("App name (must be unique): ");
            if (!appStoreAPI.isValidAppName(appName)) {
                double appSize = ScannerInput.validNextDouble("\tApp Size (in MB): ");
                double appVersion = ScannerInput.validNextDouble("\tApp Version: ");
                double appCost = ScannerInput.validNextDouble("\tApp cost: ");

                System.out.println("""
                        Which type of app is it?
                           1. Games
                           2. Education
                           3. Productivity""");
                int option = ScannerInput.validNextInt("==>> ");
                switch (option) {
                    case 1 -> {
                        //Games App
                        boolean multiplayer = Utilities.YNtoBoolean(ScannerInput.validNextChar("\tMultiplayer (Y/N): "));
                        appStoreAPI.addApp(new GameApp(developer, appName, appSize, appVersion, appCost, multiplayer));
                    }
                    case 2 -> {
                        //Education App
                        int level = ScannerInput.validNextInt("\tLevel (1-10): ");
                        appStoreAPI.addApp(new EducationApp(developer, appName, appSize, appVersion, appCost, level));
                    }
                    case 3 -> {
                        //Productivity App
                        appStoreAPI.addApp(new ProductivityApp(developer, appName, appSize, appVersion, appCost));
                    }
                }
            } else {
                System.out.println("App name already exists / App name is not valid");
            }
        } else {
            System.out.println("Developer name is NOT valid");
        }
    }

    private void updateApp() {
        System.out.println(appStoreAPI.listSummaryOfAllApps());
        String appName = ScannerInput.validNextLine("Enter the name of the app you would you like to update: ");
        if (appStoreAPI.isValidAppName(appName)) {
            App app = appStoreAPI.getAppByName(appName);
            System.out.println("""
                    Which fields do you want to update?
                      1. Developer
                      2. App Size
                      3. App Cost""");
            int option = ScannerInput.validNextInt("==>> ");
            switch (option) {
                case 1 -> app.setDeveloper(readValidDeveloperByName());
                case 2 -> app.setAppSize(ScannerInput.validNextDouble("\tApp Size (in MB): "));
                case 3 -> app.setAppCost(ScannerInput.validNextDouble("\tApp Cost: "));
            }
        } else {
            System.out.println("App name is NOT valid");
        }
    }

    private void deleteApp() {
        System.out.println(appStoreAPI.listSummaryOfAllApps());
        int appIndex = ScannerInput.validNextInt("Please enter the app index: ");
        if (appStoreAPI.deleteAppByIndex(appIndex) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    //--------------------------------------------------
    //  Reports Menu Items
    //--------------------------------------------------

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
                case 1:
                    appStoreListingByAppType();
                    break;
                case 2:
                    developerListing();
                    break;
                default:
                    System.out.println("Invalid option entered" + option);
                    break;
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
    }

    private void appStoreListingByAppType() {
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

    private void developerListing() {
        int option = ScannerInput.validNextInt("View developer report on:  " +
                "\n\t1) All Developers" +
                "\n\t2) Specific Developer " +
                "\n==>> ");

        switch (option) {
            case 1 -> {
                for (Developer developer : developerAPI.getDevelopers()) {
                    searchAppsByDeveloper(developer);
                }
            }
            case 2 -> searchAppsByDeveloper(readValidDeveloperByName());
            default -> System.out.println("Invalid option");
        }
    }

    //--------------------------------------------------
    //  Searching and Sorting
    //--------------------------------------------------
    private void searchAppsBySpecificCriteria() {
        System.out.println("""
                What criteria would you like to search apps by:
                  1) App Name
                  2) Developer Name
                  3) Rating (all apps above it)""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {
            case 1 -> searchAppsByName();
            case 2 -> searchAppsByDeveloper(readValidDeveloperByName());
            case 3 -> searchAppsAboveAStarRating();
            default -> System.out.println("Invalid option");
        }
    }

    private void searchAppsByName(){
        String appName = ScannerInput.validNextLine("\tApp Name: ");
        System.out.println(appStoreAPI.listAllAppsByName(appName));
    }

    private void searchAppsByDeveloper(Developer developer) {
        if (developer != null) {
            System.out.println(developer);
            int numberOfApps = appStoreAPI.numberOfAppsByChosenDeveloper(developer);
            System.out.println("\tTotal number of Apps: " + numberOfApps);
            if (numberOfApps != 0) {
                System.out.println(appStoreAPI.listAllAppsByChosenDeveloper(developer));
            }
        } else {
            System.out.println("No developer exists");
        }
    }

    private void searchAppsAboveAStarRating() {
        int rating = ScannerInput.validNextInt("View apps scoring higher than (1-5): ");
        System.out.println(appStoreAPI.listAllAppsAboveOrEqualAGivenStarRating(rating));
    }

    private void sortApps() {
        System.out.println("Sorting Apps by name ascending...");
        appStoreAPI.sortAppsByNameAscending();
    }

    //--------------------------------------------------
    //  Other menu item methods
    //--------------------------------------------------
    private void randomAppOfTheDay() {
        App app = appStoreAPI.randomApp();
        if (app == null) {
            System.out.println("No apps");
        } else {
            System.out.println("The random app of the day is: " + app);
        }
    }

    private void simulateRatings() {
        // simulate random ratings for all apps (to give data for recommended apps and reports etc).
        if (appStoreAPI.numberOfApps() > 0) {
            System.out.println("Simulating ratings...");
            appStoreAPI.simulateRatings();
            System.out.println(appStoreAPI.listSummaryOfAllApps());
        } else {
            System.out.println("No apps");
        }
    }

    private Developer readValidDeveloperByName() {
        String developerName = ScannerInput.validNextLine("Please enter the developer's name: ");
        if (developerAPI.isValidDeveloper(developerName)) {
            return developerAPI.getDeveloperByName(developerName);
        } else {
            return null;
        }
    }

    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
        System.out.println("Storing all data....");

        try {
            System.out.println("\t Saving developers to " + developerAPI.fileName());
            developerAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }

        try {
            System.out.println("\t Saving apps to " + appStoreAPI.fileName());
            appStoreAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadAllData() {
        System.out.println("Loading all data....");
        try {
            System.out.println("\t Loading developers from " + developerAPI.fileName());
            developerAPI.load();
        } catch (Exception e) {
            System.err.println("Error loading from this file:  " + e);
        }

        try {
            System.out.println("\t Loading apps from " + appStoreAPI.fileName());
            appStoreAPI.load();
        } catch (Exception e) {
            System.err.println("Error loading from this file:  " + e);
        }
    }

}
