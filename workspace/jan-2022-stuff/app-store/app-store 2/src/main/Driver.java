package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import controllers.LanguageAPI;
import models.Developer;
import models.EducationApp;
import persistence.JSONSerializer;
import persistence.XMLEncoderSerializer;
import persistence.XMLSerializer;
import utils.LicenseUtility;
import utils.ScannerInput;
import utils.ValidationUtility;

import java.io.File;

public class Driver {

    private LanguageAPI languageAPI;
    private DeveloperAPI developerAPI;
    private AppStoreAPI appStoreAPI;

    public static void main(String[] args) throws Exception {
        new Driver().start();
    }

    public void start() {

        languageAPI = new LanguageAPI(new JSONSerializer(new File("languages.json")));
        developerAPI = new DeveloperAPI(new XMLEncoderSerializer(new File("developers.xml")));
        appStoreAPI = new AppStoreAPI(new XMLSerializer(new File("app-store.xml")));

        loadAllData();  //load all data once the serializers are set up
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("App Store\n---------");
        System.out.println(" 1) Developer MENU");
        System.out.println(" 2) App Store MENU");
        System.out.println(" 3) Language MENU");
        System.out.println("-----------");
        System.out.println(" 4) Recommended Apps");
        System.out.println(" 99) xxxxx");
        System.out.println(" 20) Save to XML");
        System.out.println(" 21) Load from XML ");
        System.out.println("----------");
        System.out.println(" 0) Exit");
        return ScannerInput.validNextInt("==>>");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1:  runDeveloperMenu(); break;
                case 2:  runAppStoreMenu(); break;
                case 3:  runlanguageMenu(); break;
                case 4:  System.out.println(appStoreAPI.listAllRecommendedApps()); break;
                case 20: saveAllData();  break;
                case 21: loadAllData(); break;
                default: System.out.println("Invalid option entered" + option); break;
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
        System.out.println("Developer Menu");
        System.out.println(" 1) Add a developer");
        System.out.println(" 2) Delete a developer");
        System.out.println(" 3) Update developer details");
        System.out.println(" 4) List all developers");
        System.out.println(" 5) Find a developer");
        System.out.println(" 6) List apps for a specific developer");
        System.out.println(" 0) Return to main menu");
        return ScannerInput.validNextInt("==>>");
    }

    private void runDeveloperMenu() {
        int option = developerMenu();
        while (option != 0) {
            switch (option) {
                case 1:  addDeveloper(); break;
                case 2:  deleteDeveloper(); break;
                case 3:  updateDeveloper(); break;
                case 4:  System.out.println(developerAPI.listDevelopers()); break;
                case 5:  findDeveloper(); break;
                case 6:  listAppByDeveloperName(); break;
                default: System.out.println("Invalid option entered" + option); break;
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

    private void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null){
            System.out.println("Delete successful");
        }
        else{
            System.out.println("Delete not successful");
        }
    }

    private void updateDeveloper(){
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

    private void findDeveloper(){
        Developer developer = getDeveloperByName();
        if (developer == null){
            System.out.println("No developer exists");
        }
        else{
            System.out.println(developer);
        }
    }

    private void listAppByDeveloperName(){
        Developer developer = getDeveloperByName();
        if (developer == null){
            System.out.println("No developer exists");
        }
        else{
            System.out.println(appStoreAPI.listAllAppsByChosenDeveloper(developer));
        }
    }

    //---------------------
    //  App Store Menu
    //---------------------

    private int appStoreMenu() {
        System.out.println("App Store Menu");
        System.out.println(" 1) Add an app");
        System.out.println(" 2) Delete an app");
        System.out.println(" 3) List apps");
        System.out.println(" 4) .....");
        System.out.println(" 0) Return to main menu");
        return ScannerInput.validNextInt("==>>");
    }

    private void runAppStoreMenu() {
        int option = appStoreMenu();
        while (option != 0) {
            switch (option) {
                case 1:  addApp(); break;
                case 2:  deleteApp(); break;
                case 3:  System.out.println(appStoreAPI.listAllApps()); break;
                case 4:  System.out.println("to do still..."); break;
                default: System.out.println("Invalid option entered" + option); break;
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = appStoreMenu();
        }
    }

    private void addApp(){

        Developer developer = getDeveloperByName();
        if (developer != null){
            String appName = getValidAppName();
            if (!appName.equals("")){
            double appSize = ScannerInput.validNextDouble("\tApp Size (in MB): ");
            double appVersion = ScannerInput.validNextDouble("\tApp Version: ");
            int ageRating = ScannerInput.validNextInt("\tAge Rating: ");
            boolean inAppPurchase = ValidationUtility.charToBoolean(
                                        ScannerInput.validNextChar("\tIn app purchase (Y/N): "));
            String language = getValidLanguage();
            String license = getValidLicense();
            double appCost = ScannerInput.validNextDouble("\tApp cost: ");

             //todo for other types (and do category and level)
            appStoreAPI.addApp(new EducationApp(developer, appName, appSize, appVersion, ageRating, inAppPurchase, language, license, appCost, "", 0));
            }
            else{
                System.out.println("App name already exists / is not valid");
            }
        }

        else{
            System.out.println("Developer name is NOT valid");
        }
    }

    private void deleteApp(){
        //todo
    }


    //---------------------
    //  Language Menu
    //---------------------

    private int languageMenu() {
        System.out.println("Language Menu");
        System.out.println(" 1) Add a language");
        System.out.println(" 2) Delete a language");
        System.out.println(" 3) List languages");
        System.out.println(" 4) Check if language is added");
        System.out.println(" 0) Return to main menu");
        return ScannerInput.validNextInt("==>>");
    }

    private void runlanguageMenu() {
        int option = languageMenu();
        while (option != 0) {
            switch (option) {
                case 1:  addLanguage(); break;
                case 2:  deleteLanguage(); break;
                case 3:  System.out.println(languageAPI.getLanguages()); break;
                case 4:  System.out.println(isValidLanguage()); break;
                default: System.out.println("Invalid option entered" + option); break;
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = languageMenu();
        }
    }

    private void addLanguage() {
        String language = ScannerInput.validNextLine("Please enter the language: ");
        if (languageAPI.addLanguage(language)){
            System.out.println("Add successful");
        }
        else{
            System.out.println("Add not successful");
        }
    }

    private void deleteLanguage() {
        String language = ScannerInput.validNextLine("Please enter the language to delete: ");
        if (languageAPI.deleteLanguage(language)){
            System.out.println("Delete successful");
        }
        else{
            System.out.println("Delete not successful");
        }
    }

    //---------------------
    //  General Menu Items
    //---------------------

    private void saveAllData(){
        System.out.println("Storing all data....");
        try {
            languageAPI.store();
            developerAPI.store();
            appStoreAPI.store();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadAllData(){
        System.out.println("Loading all data....");
        try {
            languageAPI.load();
            developerAPI.load();
            appStoreAPI.load();
        } catch (Exception e) {
            System.err.println("Error loading from this file:  " + e);
        }
    }

    //---------------------
    //  Helper Methods
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

    private String getValidLanguage(){
        String language = ScannerInput.validNextLine("\tLanguage options " + languageAPI.getLanguages() + ": ");
        if (languageAPI.isValidLanguage(language)){
            return language;
        }
        else{
            System.err.println("\tLanguage is not valid - setting default of: " + languageAPI.getDefaultLanguage() );
            return languageAPI.getDefaultLanguage();
        }
    }

    private String isValidLanguage() {
        String language = ScannerInput.validNextLine("Language: ");
        if (languageAPI.isValidLanguage(language)){
            return "Language is valid";
        }
        else{
            return "Language is not valid";
        }
    }

}
