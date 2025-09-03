package main;

import controllers.ManufacturerAPI;
import controllers.VehicleAPI;
import models.CarbonFuelCar;
import models.ElectricCar;
import models.Scooter;
import models.Manufacturer;
import utils.ScannerInput;
import utils.Utilities;

import java.io.File;

public class Driver {



        private VehicleAPI vehicleAPI;
        private ManufacturerAPI manufacturerAPI;
        //  private AppStoreAPI appStoreAPI;

        public static void main(String[] args) throws Exception {
            new main.Driver().start();
        }

        public void start() {

            vehicleAPI = new VehicleAPI(new File("vehicles.xml"));
            manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));
            //  appStoreAPI = new AppStoreAPI(new XMLSerializer(new File("app-store.xml")));

            loadAllData();  //load all data once the serializers are set up
            runMainMenu();
        }

    private int mainMenu() {
        System.out.println("""
                         -------Vehicle Store-------------
                        |  1) Manufacturer CRUD MENU     |
                        |  2) Vehicle Store CRUD MENU    |
                        |  3) Reports MENU               |
                        |--------------------------------|
                        |  4) Search Manufacturers       |
                        |  5) Search Vehicles            |  
                        |  6) Sort Vehicles              | 
                        |--------------------------------|
                        |  10) Save all                  |
                        |  11) Load all                  |
                        |--------------------------------|
                        |  0) Exit                       |
                         --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }
    //// search todo by different criteria i.e. look at the list methods and give options based on that.
// sort todo (and give a list of options - not a recurring menu thou)
        private void runMainMenu() {
            int option = mainMenu();
            while (option != 0) {
                switch (option) {
                    case 1->  runManufacturerMenu();
                    case 2 ->  runVehicleAPIMenu();
                    case 3 -> runReportsMenu();
                    case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                    case 10 -> saveAllData();
                    case 11 -> loadAllData();
                    default ->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
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
        private int manufacturerMenu() {
            System.out.println("""
                --------Manufacturer Menu---------
               |  1) Add a manufacturer           |
               |  2) Delete a manufacturer        |
               |  3) Update manufacturer details  |
               |  4) List all manufacturers       |
               |  5) Find a manufacturer          |
               |  0) Return to main menu          |
                ----------------------------------""");
            return ScannerInput.readNextInt("==>>");
        }

        private void runManufacturerMenu() {
            int option = manufacturerMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> addManufacturer();
                    case 2 -> deleteManufacturer();
                    case 3 -> updateManufacturer();
                    case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                    case 5-> findManufacturer();
                    case 6-> listVehiclesByManufacturerName();
                    default->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = manufacturerMenu();
            }
        }

        private void addManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
            int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");

            if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))){
                System.out.println("Add successful");
            }
            else{
                System.out.println("Add not successful");
            }
        }

        private void deleteManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the developer name: ");
            if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null){
                System.out.println("Delete successful");
            }
            else{
                System.out.println("Delete not successful");
            }
        }

        private void updateManufacturer(){
            Manufacturer manufacturer = getManufacturerByName();
            if (manufacturer != null){
                int numEmployees= ScannerInput.readNextInt("Please enter number of Employees: ");
                if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees))
                    System.out.println("Number of Employees Updated");
                else
                    System.out.println("Number of Employees NOT Updated");
            }
            else
                System.out.println("Manufacturer name is NOT valid");
        }

        private void findManufacturer(){
            Manufacturer developer = getManufacturerByName();
            if (developer == null){
                System.out.println("No such manufacturer exists");
            }
            else{
                System.out.println(developer);
            }
        }

        private void listVehiclesByManufacturerName(){
            String manufacturer = ScannerInput.readNextLine("Enter the manufacturer's name:  ");

            System.out.println(manufacturerAPI.listAllVehiclesByManufacturerName(manufacturer));
        }


        //---------------------
        //  App Store Menu
        //---------------------

        private int vehicleAPIMenu() {
            System.out.println(""" 
                -----Vehicle Store Menu----- 
               | 1) Add a vehicle            |
               | 2) Delete a vehicle        |
               | 3) List all vehicles       |
               | 4) Update Vehicle          |
               | 0) Return to main menu     |
                ----------------------------""");
            return ScannerInput.readNextInt("==>>");
        }

        private void runVehicleAPIMenu() {
            int option = vehicleAPIMenu();
            while (option != 0) {
                switch (option) {
                    case 1-> addVehicle();
                    case 2-> deleteApp();
                    case 3->   System.out.println(vehicleAPI.listAllVehicles());
                    case 4-> System.out.println("todo");
                    default->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = vehicleAPIMenu();
            }
        }
        // public Vehicle(String regNumber, String  model, float cost, Manufacturer manufacturer, int  year) {
        private void addVehicle(){
            int vehicleType =  ScannerInput.readNextInt("""
        Which type of vehicle do you wish to add? 
        1) Carbon Fuel Car
        2) Electric Car
        3) Scooter """);

            Manufacturer manufacturer = getManufacturerByName();
            if (manufacturer != null){
                String regNumber = ScannerInput.readNextLine("Please enter Reg number of new Vehicle: ");


                if (vehicleAPI.isValidNewRegNumber(regNumber)){
                    String  model = ScannerInput.readNextLine("\tmodel : ");
                    float cost = ScannerInput.readNextFloat("\tcost : ");
                    int  year = ScannerInput.readNextInt("\tYear of registration");
                    switch (vehicleType) {
                        case 1, 2 -> {
                            int power = ScannerInput.readNextInt("\tpower : ");
                            int    secs0To60 = ScannerInput.readNextInt("\ttime from 0 to 60 :  ");
                            int  topSpeed = ScannerInput.readNextInt("\ttop speed : ");
                            float torque = ScannerInput.readNextFloat("\tpower: ");
                            switch (vehicleType) {
                                case 1-> {
                                    //Carbon Fuel Car
                                    String fuelType = ScannerInput.readNextLine("\tfuel type (diesel or petrol) : " );
                                    float fuelConsumption = ScannerInput.readNextFloat("\tfuel consumption : ");
                                    float carbonEmission = ScannerInput.readNextFloat("\tcarbon emission : ");
                                    int engineSize =  ScannerInput.readNextInt("\tengine size : ");
                                    char auto = ScannerInput.readNextChar("\tautomatic? y/n : ");
                                    boolean automatic = Utilities.YNtoBoolean(auto);

                                    vehicleAPI.addVehicle(new CarbonFuelCar(regNumber, model, cost, manufacturer, year, power, secs0To60,topSpeed, torque, fuelType, fuelConsumption, carbonEmission, engineSize, automatic));
                                }
                                case 2 -> {
                                    //Electric Car   - leave this todo
                                    float  engineKWatts = ScannerInput.readNextFloat("\tengine power in kWatts : ");
                                    int range = ScannerInput.readNextInt("\tengine range : ");
                                    vehicleAPI.addVehicle(new ElectricCar(regNumber, model, cost, manufacturer, year, power, secs0To60,topSpeed, torque,engineKWatts, range));
                                }
                            }
                        }
                        case 3 -> {
                            //Scooter

                            int power = ScannerInput.readNextInt("\tpower : ");
                            float weight = ScannerInput.readNextFloat("\tweight : ");
                            int topRiderWeight = ScannerInput.readNextInt("\ttop rider weight");
                            vehicleAPI.addVehicle(new Scooter(regNumber, model, cost, manufacturer, year, power, weight,topRiderWeight));

                        }
                    }
            }
                else{
                    System.out.println("Vehicle reg number  already exists.");
                }
            }

            else{
                System.out.println("Manufacturer name is NOT valid");
            }
        }

        private void deleteApp(){
            //todo
        }

    public void runReportsMenu(){
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> runManufacturerReports();
                case 2-> runVehicleReportsMenu();
                default->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
    }
    private int reportsMenu() {
        System.out.println(""" 
                --------Reports Menu ---------
               | 1) Manufacturers Overview    | 
               | 2) Vehicles Overview         |
               | 0) Return to main menu       | 
                 -----------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }
    public void runVehicleReportsMenu(){
        int option = vehicleReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> System.out.println(vehicleAPI.listAllVehicles());
                case 2-> System.out.println(vehicleAPI.listAllElectricCars());
                case 3-> System.out.println(vehicleAPI.listAllCarbonFuelCars());
                case 4-> System.out.println(vehicleAPI.listAllScooters());
                case 5 -> listAllVehiclesEqualToAGivenYear();
                case 6-> listAllVehiclesAfterToAGivenYear();
                case 7-> listAllCarbonCarsByFuelType();
                case 8 -> System.out.println(vehicleAPI.topFiveCarbonVehicles());

                default->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = vehicleReportsMenu();
        }
    }

    private int vehicleReportsMenu() {
        System.out.println(""" 
                ---------- Vehicle Reports Menu  ---------------------
               | 1) List all vehicles                                 | 
               | 2) List all Electric Cars                            |
               | 3) List all Carbon Fuel Cars                         |
               | 4) List all Scooters                                 |
               | 5) List all Vehicles registered in a given year      |
               | 6) List all Vehicles registered after a given year   |
               | 7) List all carbon fuel by fuel type                 |
               | 8) List the top five carbon vehicles                 |
               | 0) Return to main menu                               | 
                 ----------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }
    private int manufacturerReportsMenu() {
        System.out.println(""" 
                ---------- Manufacturers Reports Menu  -------------
               | 1) List Manufacturers                              | 
               | 2) List Manufacturers from a given manufacturer    |
               | 3) List Manufacturers by a given name              |
               | 0) Return to main menu                             | 
                 ---------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }
    public void runManufacturerReports() {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> System.out.println(manufacturerAPI.listManufacturers());
                case 2-> listAllVehiclesFromaGivenManufacturer();
                case 3-> System.out.println("todo");
                default->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option =  manufacturerReportsMenu();
        }
    }

//todo update methods counting methods
    public void listAllVehiclesEqualToAGivenYear() {
        int year = ScannerInput.readNextInt("What year do you want a list of cars for?  : ");
        System.out.println(vehicleAPI.listAllVehiclesEqualToAGivenYear(year));
    }
    public void listAllCarbonCarsByFuelType() {
        String fuelType= ScannerInput.readNextLine("Which fule type do you want a  list of cars for(petrol/diesel)?  : ");
        System.out.println(vehicleAPI.listAllCarbonFuelsByFuelType(fuelType));
    }
    public void listAllVehiclesFromaGivenManufacturer() {
        String manu  = ScannerInput.readNextLine("What manufacturer you want a list of cars for?  : ");
        Manufacturer m = manufacturerAPI.getManufacturerByName(manu);
        if (!(m == null))
        System.out.println(vehicleAPI.listAllVehicleByChosenManufacturer(m));
        else
            System.out.println("No manufacturer with tha name exists");
    }

    public void listAllVehiclesAfterToAGivenYear() {
        int year = ScannerInput.readNextInt("What year do you want a list of cars registered after that year for?  : ");
        System.out.println(vehicleAPI.listAllVehiclesAfterAGivenYear(year));
    }

        //---------------------
        //  General Menu Items
        //---------------------

        private void saveAllData(){
            System.out.println("Storing all data....");
            try {
                vehicleAPI.save();
                manufacturerAPI.save();
            } catch (Exception e) {
                System.err.println("Error writing to file: " + e);
            }
        }

        private void loadAllData(){
            System.out.println("Loading all data....");
            try {
                vehicleAPI.load();
                manufacturerAPI.load();
            } catch (Exception e) {
                System.err.println("Error loading from this file:  " + e);
            }
        }

        //---------------------
        //  Helper Methods
        //---------------------
//    private String getValidLicense() {
//        String license = ScannerInput.validNextLine("\tLicense options " + LicenseUtility.getLicenseKeys() + ": ");
//        if (LicenseUtility.isValidLicense(license)) {
//            return license;
//        } else {
//            System.err.println("\tLicense is not valid - setting default of: " + LicenseUtility.getDefaultLicense());
//            return LicenseUtility.getDefaultLicense();
//        }
//    }

        private String getValidRegNumber(){
            String vehicleName = ScannerInput.readNextLine("\tVehicle Reg Number (must be unique): ");
            if (vehicleAPI.isValidNewRegNumber(vehicleName)) {
                return vehicleName;
            } else {
                System.err.println("\tApp name already exists / is not valid.");
                return "";
            }
        }

        private Manufacturer getManufacturerByName(){
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer's name: ");
            if (manufacturerAPI.isValidManufacturer(manufacturerName)){
                return manufacturerAPI.getManufacturerByName(manufacturerName);
            }
            else{
                return null;
            }
        }

//    private String getValidLanguage(){
//        String language = ScannerInput.readNextLine("\tLanguage options " + vehicleAPI.getLanguages() + ": ");
//        if (vehicleAPI.isValidLanguage(language)){
//            return language;
//        }
//        else{
//            System.err.println("\tLanguage is not valid - setting default of: " + vehicleAPI.getDefaultLanguage() );
//            return vehicleAPI.getDefaultLanguage();
//        }
//    }

//    private String isValidLanguage() {
//        String language = ScannerInput.readNextLine("Language: ");
//        if (vehicleAPI.isValidLanguage(language)){
//            return "Language is valid";
//        }
//        else{
//            return "Language is not valid";
//        }
//    }

    }

